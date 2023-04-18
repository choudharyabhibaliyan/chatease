package org.durga.repository;

import org.durga.entites.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer> {
    @Query("select m from Module m  JOIN m.role r where r.role=?1")
    //select m from Module m JOIN Role r where r.roleId = m.ref_roleId
    List<Module> findModulesByRole(String role);
}
