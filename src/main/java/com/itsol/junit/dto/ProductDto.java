package com.itsol.junit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDto {
    private Long id;
    private String productName;
    private Long quantity;
}
