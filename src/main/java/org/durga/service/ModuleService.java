package org.durga.service;

import org.durga.entites.Module;
import org.durga.repository.ModuleRepository;
import org.durga.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private RoleRepository roleRepository;
    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }


    public List<String> getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<GrantedAuthority> roles =((User) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal()).getAuthorities();
        String role = String.valueOf(roles.stream().findFirst().get());
        List<Module> modules = moduleRepository.findModulesByRole(role);
        List<String> moduleNames = modules.stream().map(e-> e.getModuleName()).collect(Collectors.toList());
        return moduleNames;

    }

}
