package org.durga.service;

import org.durga.entites.Post;
import org.durga.entites.User;
import org.durga.repository.PostRepository;
import org.durga.repository.UserRepository;
import org.durga.utilites.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PostServiceImpl {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public static String NOTIFICATION_DIRECTORY = System.getProperty("user.dir") + "/notification";

    public Post newPost(int userId, MultipartFile image) throws IOException {
        Path fileNameAndPath  = UserUtility.addNewImage(image , NOTIFICATION_DIRECTORY);
        Post post= new Post();
        post.setImage(fileNameAndPath.toString());
        post.setPostBy(userRepository.findById(userId).get());
        postRepository.save(post);
        post.setImage(UserUtility.addProfilePic(post.getImage()));
        return post;
    }

    public Post updateTextOnPost(int userId, Post post) throws IOException {
        Post savedPost = postRepository.findById(post.getId()).get();
        savedPost.setPostText(post.getPostText());
        postRepository.save(savedPost);
        savedPost.setImage(UserUtility.addProfilePic(savedPost.getImage()));
        return savedPost;
    }

    public List<Post> getAllPost() throws IOException {
        List<Post> posts = postRepository.findAll();
        for(Post post : posts){
            post.setImage(UserUtility.addProfilePic(post.getImage()));
        }
        return posts;
    }
}
