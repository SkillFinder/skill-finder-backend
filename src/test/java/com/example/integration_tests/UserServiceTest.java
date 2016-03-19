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

    public static final String COUNTRY = "Cracow";
    public static final String CITY = "Poland";
    public static final String SKILL = "english";
    private final String EMAIL_ADDRESS = "qqq@gmail.com";
    public static final String USERNAME = "Tom";

    @Autowired
    private UserService userService;

    @Test
    public void shouldBePossibleToSaveAndRemoveUser() {
            User user = new User(USERNAME, EMAIL_ADDRESS,new Locality(CITY, COUNTRY));
            assertThat(userService.getUser(EMAIL_ADDRESS)).isNull();
            userService.save(user);
            assertThat(userService.getUser(EMAIL_ADDRESS)).isNotNull();
            userService.removeUser(EMAIL_ADDRESS);
            assertThat(userService.getUser(EMAIL_ADDRESS)).isNull();
    }

    @Test
    public void shouldBePossibleToUpdateUser() {
        User user = new User(USERNAME, EMAIL_ADDRESS,new Locality(CITY,COUNTRY));
        userService.save(user);
        assertThat(userService.getUser(EMAIL_ADDRESS)).isNotNull();
        User updatedUser = new User(USERNAME, EMAIL_ADDRESS, new Locality(CITY,COUNTRY));
        Skill skill = new Skill(SKILL, Level.FIFTH);
        updatedUser.setSkills(Collections.singletonList(skill));
        userService.save(updatedUser);
        User dbUser = userService.getUser(EMAIL_ADDRESS);
        assertThat(dbUser).isNotNull();
        assertThat(dbUser.getSkills()).contains(skill);
        userService.removeUser(EMAIL_ADDRESS);
    }

    @Test
    public void shouldBePossibleToAddPhoto() throws IOException {
        User user = new User(USERNAME, EMAIL_ADDRESS,new Locality(CITY,COUNTRY));
        userService.save(user);
        InputStream inputStream = UserServiceTest.class.getResourceAsStream("/image.jpg");
        String photoID = userService.addPhoto(inputStream, EMAIL_ADDRESS);

        InputStream photo = userService.getPhoto(photoID);

        assertThat(IOUtils.contentEquals(inputStream, photo));
    }



}
