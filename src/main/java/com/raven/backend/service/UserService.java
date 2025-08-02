package com.raven.backend.service;

import com.raven.backend.entity.User;
import com.raven.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> findUserById(int id) {
        return userRepo.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(int id, User user) {
        User userToUpdate = userRepo.findById(id).orElseThrow();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        return userRepo.save(userToUpdate);
    }

    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

}
