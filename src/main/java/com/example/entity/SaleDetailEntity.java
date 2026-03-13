package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sale_details",
        uniqueConstraints = @UniqueConstraint(name = "uk_sale_detail_sale_product", columnNames = {"id_sale", "id_product"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSaleDetail;

    private Integer quantity;
    private Double unitPrice;
    private Double total;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "fk_product_sale_detail"))
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sale", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_sale_detail"))
    private SaleEntity sale;
}
