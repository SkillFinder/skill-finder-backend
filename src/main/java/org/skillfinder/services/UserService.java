package org.skillfinder.services;

import org.skillfinder.models.User;

import java.io.InputStream;
import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUser(String login);

    void save(User user);

    void removeUser(String id);

    String addPhoto(InputStream photo, String login);

    InputStream getPhoto(String id);

}
