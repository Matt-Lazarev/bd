package com.lazarev.bd.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerId;

    private String firstname;
    private String lastname;
    private String phoneNumber;

    @OneToMany
    @JoinColumn(name = "manager_id", referencedColumnName = "managerId")
    private Set<Contract> contracts;
}
