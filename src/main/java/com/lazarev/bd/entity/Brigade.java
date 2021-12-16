package com.lazarev.bd.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "brigade")
public class Brigade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brigadeId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "objectId")
    private Object object;
}
