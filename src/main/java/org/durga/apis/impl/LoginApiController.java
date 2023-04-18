package org.durga.apis.impl;

import org.durga.model.LoginResponse;
import org.durga.entites.User;
import org.durga.service.UserService;
import org.durga.utilites.UserUtility;
import org.durga.utilites.ResponceHandler;
import org.durga.model.JWTRequest;
import org.durga.service.CustomUserDetailsService;
import org.durga.utilites.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@CrossOrigin("*")
public class LoginApiController {
    @Autowired
    private CustomUserDetailsService customUserDetail;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<Object> generateToken(@RequestBody JWTRequest jwtRequest, HttpSession session) throws IOException {
        LoginResponse loginResponse = new LoginResponse();
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch(UsernameNotFoundException e) {
            e.printStackTrace();
        }
        UserDetails userDetail = this.customUserDetail.loadUserByUsername(jwtRequest.getUsername());
        loginResponse.setToken(jwtUtil.generateToken(userDetail));
        User user = userService.getDataByName(userDetail.getUsername());
        loginResponse.setId(user.getId());
        session.setAttribute("user", user);
        if(user.getProfilePic() != null) {
            loginResponse.setProfilePic(UserUtility.addProfilePic(user.getProfilePic()));
        }else{
            loginResponse.setProfilePic("");
        }
        return ResponceHandler.generateResponce(HttpStatus.OK, true ,loginResponse);
    }

}
