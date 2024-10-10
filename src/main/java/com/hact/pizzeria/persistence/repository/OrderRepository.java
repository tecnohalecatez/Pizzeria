package com.hact.pizzeria.persistence.repository;

import com.hact.pizzeria.persistence.entity.OrderEntity;
import com.hact.pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizzeria.pizza_order WHERE id_customer = :idCustomer", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("idCustomer") String idCustomer);

    @Query(value = "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
            "po.total AS orderTotal, STRING_AGG(pi.name, ',') AS pizzaNames " +
            "FROM pizzeria.pizza_order po " +
            "INNER JOIN pizzeria.customer cu ON po.id_customer = cu.id_customer  " +
            "INNER JOIN pizzeria.order_item oi ON po.id_order = oi.id_order " +
            "INNER JOIN pizzeria.pizza pi ON oi.id_pizza = pi.id_pizza  " +
            "WHERE po.id_order = :orderId  " +
            "GROUP BY po.id_order, cu.name, po.date, po.total;", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") Integer orderId);

    @Procedure(procedureName = "pizzeria.take_random_pizza_order", outputParameterName = "order_taken")
    Boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);

}
