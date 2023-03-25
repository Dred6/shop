package com.davydovandrey.shop.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer estimation;
    private String review;

    private Date date;
    @ManyToOne
    private Person buyer;

    public Purchase(Product product, int estimation, String review) {
        this.product = product;
        this.estimation = estimation;
        this.review = review;
    }
}
