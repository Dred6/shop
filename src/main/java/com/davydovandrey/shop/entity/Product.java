package com.davydovandrey.shop.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    private int price;

    private int quantityStock;

    @OneToMany
    private List<InformationAboutDiscounts> informationAboutDiscounts;
    @ElementCollection
    private List<String> listReview;
    @ElementCollection
    private List<String> listKeywords;
    private double averageRating;
    private boolean activity;

    public Product(String name, String description, Organization organization, int price, int quantityStock,
                   List<InformationAboutDiscounts> informationAboutDiscounts, List<String> listReview,
                   List<String> listKeywords, int averageRating) {
        this.name = name;
        this.description = description;
        this.organization = organization;
        this.price = price;
        this.quantityStock = quantityStock;
        this.informationAboutDiscounts = informationAboutDiscounts;
        this.listReview = listReview;
        this.listKeywords = listKeywords;
        this.averageRating = averageRating;
    }
}
