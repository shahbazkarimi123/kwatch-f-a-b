package com.kwatch.user_details.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kwatch.user_details.service.UserService;
import com.kwatch.user_details.userDetails.User;

// @CrossOrigin(origins = "http://192.168.1.13:4200")
@RestController
public class UserRestController {

    private final PasswordEncoder passwordEncoder;
    // private final Logger logger =
    // LoggerFactory.getLogger(UserRestController.class);

    // private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;
    @Autowired
    UserRestController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("users/login")
    public ResponseEntity<?> getUserByEmail(@RequestBody LoginRequest loginData) {
        User theUser = userService.getUserByEmail(loginData.getEmail());
        
        if (theUser ==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email");
        }
        if (passwordEncoder.matches(loginData.getPassword(), theUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(theUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }

    @PostMapping(path = "/users/profile")
    public void uploadProfilePicture(@RequestParam("id") Long id) {
        userService.getUserById(id);
        


    }

    @GetMapping(path = "/users/{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

    @PostMapping(path = "/users/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(path = "/users/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        String message = userService.deleteUserById(userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

}

class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

