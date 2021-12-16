package com.lazarev.bd.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "object")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectId;

    private String name;
    private String description;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "projectId")
    private Project project;
}
