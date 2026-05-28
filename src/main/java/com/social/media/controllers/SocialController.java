package com.social.media.controllers;

import com.social.media.models.User;
import com.social.media.payload.UserRequestDTO;
import com.social.media.payload.UserUpdateDTO;
import com.social.media.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialController {

    @Autowired
    private SocialService socialService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(
                socialService.getAllUser(),
                HttpStatus.OK);
    }

    @PostMapping("/createUsers")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO dto) throws Exception {
        User newUser = socialService.createUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newUser);
    }

    @PutMapping("/updateUserDetail/{id}")
    public ResponseEntity<User> updateUserDetail(@PathVariable Long id, @RequestBody UserUpdateDTO dto){
        User updatedUser = socialService.updateUserDetail(id,dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        socialService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
