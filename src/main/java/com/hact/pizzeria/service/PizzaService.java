package com.hact.pizzeria.service;

import com.hact.pizzeria.persistence.entity.PizzaEntity;
import com.hact.pizzeria.persistence.repository.PizzaRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll(){
        return pizzaRepository.findAll();
    }

    public PizzaEntity get (Integer id){
        return this.pizzaRepository.findById(id).orElse(null);
    }
}
