package org.example.sunsetresortwebapp.Services;

import org.example.sunsetresortwebapp.Models.User;

import java.util.List;
import java.util.Optional;

public interface IUserSerice {
    User getUserByEmail(String email);
     User getUserById(long id);
    User saveUser(User user);
    void deleleteUserById(long id);
    User  updateUser(User user , long id);
    List<User> getAllUsers();
    void deleteAllUsers();
}
