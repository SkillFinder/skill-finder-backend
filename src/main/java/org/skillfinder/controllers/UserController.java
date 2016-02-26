package org.skillfinder.controllers;

import org.skillfinder.models.User;
import org.skillfinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> usersList() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<User> createUsers(@RequestBody List<User> users) {
        return userRepository.save(users);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.delete(id);
    }
}
