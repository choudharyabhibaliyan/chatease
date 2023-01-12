package org.durga.user_service.controller.loginController;

import org.durga.common.ResponceHandler;
import org.durga.user_service.models.user.JWTRequest;
import org.durga.user_service.service.CustomUserDetailsService;
import org.durga.user_service.util.JWTUtil;
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

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private CustomUserDetailsService customUserDetail;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<Object> generateToken(@RequestBody JWTRequest jwtRequest){
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch(UsernameNotFoundException e) {
            e.printStackTrace();
        }
        UserDetails userDetail = this.customUserDetail.loadUserByUsername(jwtRequest.getUsername());
        return ResponceHandler.generateResponce(HttpStatus.OK, true ,jwtUtil.generateToken(userDetail));
    }

}
