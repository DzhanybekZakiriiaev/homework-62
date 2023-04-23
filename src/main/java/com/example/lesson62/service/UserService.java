package com.example.lesson62.service;

import com.example.lesson62.dao.UserDao;
import com.example.lesson62.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getUsersFromName(String name) {
        return userDao.getUsersFromName(name);
    }

    public List<User> getUserFromAccountName(String name) {
        return userDao.getUsersFromAccountName(name);
    }

    public List<User> getUserFromEmail(String email) {
        return userDao.getUsersFromEmail(email);
    }

    public String checkUserFromEmail(String email) {
        var emails = userDao.checkUserFromEmail(email);
        return Objects.requireNonNullElse(emails, "s23dal3213sj32323ajl42dj24sdl2sjd2dsd");
    }

    public String checkUserFromAccountName(String accountName) {
        var name = userDao.checkUserFromAccountName(accountName).getAccountName();
        return Objects.requireNonNullElse(name, "sh0129lsf012kd9d21das23wf2");
    }

    public void saveToBase(User user) {
        userDao.saveToBase(user);
    }

    public boolean login(String usernameOrEmail, String password) {
        return userDao.login(usernameOrEmail, password);
    }

    public List<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
