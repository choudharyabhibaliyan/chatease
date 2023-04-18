package org.durga.entites;

import lombok.*;

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
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> role;
}
