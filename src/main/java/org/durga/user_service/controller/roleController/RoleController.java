package org.durga.user_service.controller.roleController;

import org.durga.common.ResponceHandler;
import org.durga.user_service.models.user.Role;
import org.durga.user_service.service.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create_role")
    public ResponseEntity<Object> craeteRoles(@RequestBody Role role){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , roleService.createRole(role));
    }

    @GetMapping("/get_role")
    public ResponseEntity<Object> getRoles(){
        return ResponceHandler.generateResponce(HttpStatus.OK , true , roleService.getRoles());
    }
}
