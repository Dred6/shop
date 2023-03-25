package com.davydovandrey.shop.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Transient
    @OneToMany(mappedBy = "role")
    private Set<Person> users = new HashSet<>();

    public Role(String name, Set<Person> users) {
        this.name = name;
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }


}
