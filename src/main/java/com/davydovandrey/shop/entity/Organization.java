package com.davydovandrey.shop.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    @ManyToOne
    private Person owner;
    private boolean activity;
    private boolean deleted;
    private boolean blocked;

    @OneToMany
    private List<Product> productList;

    public Organization(String name, String description, List<Product> productList) {
        this.name = name;
        this.description = description;
        this.productList = productList;
    }
}
