// package com.uade.tpo.courseCommerce.entity;


// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.Table;
// import lombok.Data;


// @Entity
// @Table(name = "payments")
// @Data
// public class Payment {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(nullable = false)
//     private long id_payment;
    
//     @Column(nullable = false)
//     private String paymentMethod;

//     @Column(nullable = false)
//     private String paymentDay;

//     @OneToOne
//     @JoinColumn(name= "cart_id")
//     private Cart cart;


// }
