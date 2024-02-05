package com.example.estorecartservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    private BigDecimal totalPrice;

    @Transient
    private  Instant instant = Instant.now();

    @OneToMany(cascade = CascadeType.ALL)
    List<Product> productList;

}
