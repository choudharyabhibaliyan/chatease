package org.durga.user_service.controller.moduleController;

import org.durga.common.ResponceHandler;
import org.durga.user_service.models.module.Module;
import org.durga.user_service.service.moduleService.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/create_module")
    public ResponseEntity<Object> craeteModule(@RequestBody Module module){
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,moduleService.addModule(module));
    }

    @GetMapping("/getHomeData")
    public ResponseEntity<Object> craeteModule(Authentication authentication){
        final UserDetails currentUser = (UserDetails) authentication.getPrincipal();
        System.out.println(currentUser.getAuthorities());
        System.out.println(currentUser.getUsername());
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,"getHomeData");
    }

}
