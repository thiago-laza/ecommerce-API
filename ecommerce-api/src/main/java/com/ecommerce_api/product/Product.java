package com.ecommerce_api.product;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    @Size(max = 100)
    private String name;

    
    @NotNull
    @Column(nullable = false)
    @PositiveOrZero
    private Double price;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    @Size(max = 500)
    private String description;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String category;

    
    @NotNull
    @Column(nullable = false)
    private int stock;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate entryDate;
}
