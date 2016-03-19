package org.skillfinder.controllers;

import org.skillfinder.models.User;
import org.skillfinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> usersList() {
        return userService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        userService.removeUser(id);
    }

    @RequestMapping(value="/photo", method = RequestMethod.POST)
    public void addPhoto(InputStream photo, Principal principal) {
        userService.addPhoto(photo, principal.getName());
    }

    @RequestMapping(value="/photo", method = RequestMethod.GET)
    public InputStream getPhoto(String id) {
        return userService.getPhoto(id);
    }

}
