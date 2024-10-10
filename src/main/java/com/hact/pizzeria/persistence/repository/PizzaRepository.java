package com.hact.pizzeria.persistence.repository;

import com.hact.pizzeria.persistence.entity.PizzaEntity;
import com.hact.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);
    int countByVeganTrue();

    @Query(value = "UPDATE pizzeria.pizza " +
            "SET price = :#{#updatePizza.newPrice} " +
            "WHERE id_pizza = :#{#updatePizza.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("updatePizza") UpdatePizzaPriceDto updatePizza);
}
