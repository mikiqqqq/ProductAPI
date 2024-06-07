package com.example.product_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "NARUDZBA_PROIZVODI")
public class OrderItem {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NARUDZBA_ID")
    private Integer orderId;

    @Column(name = "PROIZVOD_ID")
    private Integer itemId;
}
