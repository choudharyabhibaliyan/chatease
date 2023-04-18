package org.durga.service;

import org.durga.entites.User;
import org.durga.repository.UserFriendsRepository;
import org.durga.repository.UserRepository;
import org.durga.utilites.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserFriendsRepository userFriendsRepository;
    @Autowired
    private RoleService roleService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/profilePics";

    public User getDataByName(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getUser() {
        return userRepo.findAll();
    }

    public User crateUser(User user) {
        user.setRole(roleService.findRoleById(3));
        return userRepo.save(user);
    }

    public User getUserDetails(int id) throws IOException {
        User user = userRepo.findById(id).get();
        if(user.getProfilePic()!=null) {
            user.setProfilePic(UserUtility.addProfilePic(user.getProfilePic()));
        }
        return user;
    }
    public String uploadProfilePicture(int id, MultipartFile image) throws IOException {
        Path fileNameAndPath  = UserUtility.addNewImage(image , UPLOAD_DIRECTORY);
        User user = userRepo.findById(id).get();
        user.setProfilePic(fileNameAndPath.toString());
        userRepo.save(user);
        return UserUtility.addProfilePic(user.getProfilePic());
    }

}
