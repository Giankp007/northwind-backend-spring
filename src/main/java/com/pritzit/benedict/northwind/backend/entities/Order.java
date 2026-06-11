package com.pritzit.benedict.northwind.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "customer_id", length = 5)
    private String customerId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "ship_name", length = 40)
    private String shipName;

    @Column(name = "ship_city", length = 15)
    private String shipCity;

    @Column(name = "ship_country", length = 15)
    private String shipCountry;

    @OneToMany
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private List<OrderDetail> orderDetails;
}
