package za.co.taskmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.taskmanager.model.User;
import za.co.taskmanager.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User userRequest){
        User user = userService.saveUser(userRequest);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("")
    public Iterable<User> getAllUsers(){return userService.findAllUsers();}

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User userRequest){
        User user = userService.UpdateUser(userId, userRequest);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> userService(@PathVariable Long userId){
        User user = userService.findUserById(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<String>("User with ID: '"+userId+"' was deleted", HttpStatus.OK);
    }
}
