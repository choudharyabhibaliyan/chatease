package org.durga.user_service.service.moduleService;

import org.durga.user_service.models.module.Module;
import org.durga.user_service.repository.moduleRepository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }
}
