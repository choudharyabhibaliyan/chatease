package org.durga.apis.impl;

import org.durga.entites.Post;
import org.durga.service.PostServiceImpl;
import org.durga.utilites.ResponceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PostApiController {
    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/post/{userId}")
    public ResponseEntity<Object> newPost(@RequestParam("image") MultipartFile image, @PathVariable int userId) throws IOException {
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,postService.newPost(userId , image));
    }

    @PutMapping("/post/{userId}")
    public ResponseEntity<Object> updateTextOnPost( @PathVariable int userId,@RequestBody Post post) throws IOException {
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,postService.updateTextOnPost(userId , post));
    }


    @GetMapping("/post")
    public ResponseEntity<Object> getAllPost() throws IOException {
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,postService.getAllPost());
    }
}
