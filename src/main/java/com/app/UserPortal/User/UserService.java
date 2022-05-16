package com.app.UserPortal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User us){
        return userRepository.save(us);
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public User updateUser(User us){
        return userRepository.save(us);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
