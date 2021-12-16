package com.lazarev.bd.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workerId;

    private String firstname;
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "brigade_id", referencedColumnName = "brigadeId")
    private Brigade brigade;
}

