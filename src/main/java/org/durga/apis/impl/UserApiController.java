package org.durga.apis.impl;

import org.durga.utilites.ResponceHandler;
import org.durga.entites.User;
import org.durga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/getCurrentUser/{id}")
    public ResponseEntity<Object> getUserDetails(@PathVariable int id) throws IOException {
        return ResponceHandler.generateResponce(HttpStatus.OK , true , userService.getUserDetails(id));
    }

    @PostMapping("/uploadProfilePicture/{id}")
    public ResponseEntity<Object> uploadProfilePicture(@RequestParam("image") MultipartFile image,@PathVariable int id) throws IOException {
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,userService.uploadProfilePicture(id , image));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , userService.crateUser(user));
    }

}
