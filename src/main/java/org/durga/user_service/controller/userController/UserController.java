package org.durga.user_service.controller.userController;

import org.durga.common.ResponceHandler;
import org.durga.user_service.models.user.User;
import org.durga.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get_user")
    public ResponseEntity<Object> getRoles(){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , userService.getUser());
    }
    @PostMapping("/create_user")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , userService.crateUser(user));
    }

}
