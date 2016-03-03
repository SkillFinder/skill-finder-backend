package org.skillfinder.configurations;

import org.skillfinder.models.Account;
import org.skillfinder.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    private UserDetailsService userDetailsService() {
        return username -> {
            Account account = accountRepository.findByUsername(username);
            if (account != null) {
                return new User(account.getUsername(), account.getPasswordHash(),
                        true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
            }
            else {
                throw new UsernameNotFoundException("Could not find the user: " + username);
            }
        };
    }
}