package org.durga.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    static final long serialVersionUID = -5164301389993019513L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String role;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Module> modules;
}
