package org.durga.apis.impl;

import org.durga.utilites.ResponceHandler;
import org.durga.entites.Role;
import org.durga.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleApiController {

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
