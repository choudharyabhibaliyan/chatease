package org.durga.apis.impl;

import org.durga.utilites.ResponceHandler;
import org.durga.entites.Module;
import org.durga.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization"},exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
public class ModuleApiController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/create_module")
    public ResponseEntity<Object> craeteModule(@RequestBody Module module){
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,moduleService.addModule(module));
    }

    @GetMapping("/getHomeData")
    public ResponseEntity<Object> craeteModule(){
        return ResponceHandler.generateResponce(HttpStatus.OK , true ,moduleService.getCurrentUser());
    }

}
