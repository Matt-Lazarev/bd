package com.lazarev.bd.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    private String firstname;
    private String lastname;
    private String paymentAccount;

    @OneToMany
    @JoinColumn(name = "client_id", referencedColumnName = "clientId")
    private Set<Contract> contracts;
}
