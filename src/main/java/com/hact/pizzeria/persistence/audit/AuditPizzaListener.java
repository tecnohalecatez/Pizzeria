package com.hact.pizzeria.persistence.audit;

import com.hact.pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity pizzaCurrent;

    @PostLoad
    public void onPostLoad(PizzaEntity pizza) {
        System.out.println("POST LOAD");
        this.pizzaCurrent = SerializationUtils.clone(pizza);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizza) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.pizzaCurrent.toString());
        System.out.println("NEW VALUE: " + pizza.toString());
    }

    @PreRemove
    public void onPreRemove(PizzaEntity pizza) {
        System.out.println(pizza.toString());
    }
}
