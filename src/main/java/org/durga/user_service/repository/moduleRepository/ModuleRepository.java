package org.durga.user_service.repository.moduleRepository;

import org.durga.user_service.models.module.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer> {
}
