package org.skillfinder.services;

import org.skillfinder.models.User;
import org.skillfinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ImageService imageService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String login) {
        return userRepository.findByEmail(login);
    }

    @Override
    public void save(User user) {
        User dbUser = getUser(user.getEmail());
        if(dbUser != null) {
            user.setId(dbUser.getId());
        }
        userRepository.save(user);
    }


    @Override
    public void removeUser(String id) {
        userRepository.removeByEmail(id);
    }

    @Override
    public String addPhoto(InputStream photo, String login) {
        String imageID = imageService.saveImage(photo, login);
        User userDB = userRepository.findByEmail(login);
        userDB.getImageIDs().add(imageID);
        userRepository.save(userDB);
        return imageID;
    }

    @Override
    public InputStream getPhoto(String id) {
        return imageService.getFile(id);
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
