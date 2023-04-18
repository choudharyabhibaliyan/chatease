package org.durga.utilites;

import org.durga.entites.User;
import org.durga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class UserUtility {

    @Autowired
    private UserRepository userRepository;
    public static String addProfilePic(String imgPath) throws IOException {
        Path imagePath = Paths.get(imgPath);
        byte[] imageBytes = Files.readAllBytes(imagePath);
        UserUtility userUtility = new UserUtility();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static Path addNewImage(MultipartFile image,String directory) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(directory, image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        Files.write(fileNameAndPath, image.getBytes());
        return fileNameAndPath;
    }

}
