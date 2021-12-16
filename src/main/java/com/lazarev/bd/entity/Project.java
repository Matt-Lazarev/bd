package com.lazarev.bd.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    private LocalDate workStartAt;
    private LocalDate workEndAt;
    private String description;

    @OneToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "contractId")
    private Contract contract;
}
