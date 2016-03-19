package com.example.integration_tests;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skillfinder.SkillFinderBackendApplication;
import org.skillfinder.enums.Level;
import org.skillfinder.models.Locality;
import org.skillfinder.models.Skill;
import org.skillfinder.models.User;
import org.skillfinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SkillFinderBackendApplication.class)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    private final String EMAIL = "qqq@gmail.com";

    @Test
    public void shouldBePossibleToSaveAndRemoveUser() {
            User user = new User("Dawid",EMAIL,new Locality("Poland","Cracow"));
            assertThat(userService.getUser(EMAIL)).isNull();
            userService.save(user);
            assertThat(userService.getUser(EMAIL)).isNotNull();
            userService.removeUser(EMAIL);
            assertThat(userService.getUser(EMAIL)).isNull();
    }

    @Test
    public void shouldBePossibleToUpdateUser() {
        User user = new User("Dawid",EMAIL,new Locality("Poland","Cracow"));
        userService.save(user);
        assertThat(userService.getUser(EMAIL)).isNotNull();
        User updatedUser = new User("Tom",EMAIL, new Locality("Poland","Cracow"));
        Skill skill = new Skill("english", Level.FIFTH);
        updatedUser.setSkills(Collections.singletonList(skill));
        userService.save(updatedUser);
        User dbUser = userService.getUser(EMAIL);
        assertThat(dbUser).isNotNull();
        assertThat(dbUser.getSkills()).contains(skill);
        userService.removeUser(EMAIL);
    }

    @Test
    public void shouldBePossibleToAddPhoto() throws IOException {
        User user = new User("Dawid",EMAIL,new Locality("Poland","Cracow"));
        userService.save(user);
        InputStream inputStream = UserServiceTest.class.getResourceAsStream("/abc.txt");
        String photoID = userService.addPhoto(inputStream, EMAIL);

        InputStream photo = userService.getPhoto(photoID);

        assertThat(IOUtils.contentEquals(inputStream, photo));
    }



}
