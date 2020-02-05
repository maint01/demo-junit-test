package com.itsol.junit.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "TBL_PRODUCT")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_PRODUCT_SEQ")
    @SequenceGenerator(name = "TBL_PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "QUANTITY")
    private Long quantity;
}
