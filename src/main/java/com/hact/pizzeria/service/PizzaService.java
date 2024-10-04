package com.hact.pizzeria.service;

import com.hact.pizzeria.persistence.entity.PizzaEntity;
import com.hact.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.hact.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public Page<PizzaEntity> getAll(int page, int size) {
        return this.pizzaPagSortRepository.findAll(PageRequest.of(page, size));
    }

    public List<PizzaEntity> getAvailable() {
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public Page<PizzaEntity> getAvailable(int page, int size, String sortBy, String sortDirection) {
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaPagSortRepository.findByAvailableTrue(
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy)));
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(Double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity get(Integer id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }

    public void delete(Integer idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean exists(Integer idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }
}
