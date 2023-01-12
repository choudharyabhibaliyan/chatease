package org.durga.user_service.service;

import org.durga.user_service.models.user.User;
import org.durga.user_service.repository.userRepository.UserRepository;
import org.durga.user_service.service.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleService roleService;
    public User getDataByName(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getUser() {
        return userRepo.findAll();
    }

    public User crateUser(User user) {
        user.setRole(roleService.findRoleById(user.getRole().getId()));
        return userRepo.save(user);
    }


}
