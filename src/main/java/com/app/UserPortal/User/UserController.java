package com.app.UserPortal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User us){
        if (us.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        User entity = userService.addUser(us);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        Optional<User> user = userService.getUser(id);
        if(user.isPresent())
            return ResponseEntity.ok().body(user.get());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User us){
        if(us.getId()==null)
            return ResponseEntity.badRequest().build();
        User result = userService.updateUser(us);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id){
        if(!userService.getUser(id).isPresent())
            return ResponseEntity.notFound().build();
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
