package com.hact.pizzeria.service;

import com.hact.pizzeria.persistence.entity.PizzaEntity;
import com.hact.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable() {
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
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
