package com.davydovandrey.shop.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class InformationAboutDiscounts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    private List<Product> productList;

    private int amountDiscount;

    private int durationDiscount;

    private Date startDate;

    private Date endDate;

    public InformationAboutDiscounts(List<Product> productList, int amountDiscount, int durationDiscount, Date startDate, Date endDate) {
        this.productList = productList;
        this.amountDiscount = amountDiscount;
        this.durationDiscount = durationDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
