package com.example.repository;

import com.example.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findAllByEnabledIsTrueOrderByIdSaleDesc();

    Optional<SaleEntity> findFirstByEnabledIsTrueAndIdSale(Long idSale);

    @Query("SELECT s FROM SaleEntity s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    List<SaleEntity> findSalesByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<SaleEntity> findAllByEnabledIsTrueAndCustomer_IdCustomer(Long idCustomer);

}
