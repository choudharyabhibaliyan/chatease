package org.durga.apis.impl;

import org.durga.service.FriendsServiceImpl;
import org.durga.utilites.ResponceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendsApiController {

    @Autowired
    private FriendsServiceImpl friendsService;

    @GetMapping("/findUnknownUsers/{id}")
    public ResponseEntity<Object> findUnknownUsers(@PathVariable int id){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.getAllUnknownUsers(id));
    }

    @PutMapping("/sendFriendRequest/{userId}/{friendId}")
    public ResponseEntity<Object> sendFriendRequest(@PathVariable int userId , @PathVariable int friendId){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.sendFriendRequest(userId ,friendId ));
    }

    @GetMapping("/getAllSendRequestsByUser/{id}")
    public ResponseEntity<Object> getAllSendRequestsByUser(@PathVariable int id){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.getAllSendRequestsByUser(id));
    }

    @PutMapping("/acceptRequest/{id}/{requestUserId}")
    public ResponseEntity<Object> acceptRequest(@PathVariable int id , @PathVariable int requestUserId){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.acceptRequest(id ,requestUserId , "ACTIVE"));
    }

    @GetMapping("/getAllRequestsByUser/{id}")
    public ResponseEntity<Object> getAllRequestsByUser(@PathVariable int id){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.getAllRequestsByUser(id));
    }

    @GetMapping("/getAllFriends/{id}")
    public ResponseEntity<Object> getAllFriends(@PathVariable int id){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.getAllFriends(id));
    }

    @PutMapping("/deleteSentRequest/{id}/{requestUserId}")
    public ResponseEntity<Object> deleteRequest(@PathVariable int id , @PathVariable int requestUserId){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.deleteRequest(id ,requestUserId , "NONE"));
    }

    @PutMapping("/deleteIncomingRequest/{id}/{requestUserId}")
    public ResponseEntity<Object> deleteIncomingRequest(@PathVariable int id , @PathVariable int requestUserId){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.deleteIncomingRequest(id ,requestUserId , "NONE"));
    }

    @PutMapping("/unfriend/{id}/{friendId}")
    public ResponseEntity<Object> unfriend(@PathVariable int id , @PathVariable int friendId){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , friendsService.unfriend(id ,friendId , "ACTIVE"));
    }
}
