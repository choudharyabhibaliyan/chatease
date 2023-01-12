package org.durga.user_service.models.module;

import lombok.*;
import org.durga.user_service.models.user.Role;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int moduleNo;
    private String moduleName;
    @ManyToMany
    private List<Role> role;
}
