package com.lazarev.bd.entity;

//                          material_id INT NOT NULL AUTO_INCREMENT,
//                          name VARCHAR(45) NOT NULL,
//                          unit VARCHAR(10) DEFAULT NULL,
//                          price INT NOT NULL,
//                          PRIMARY KEY (material_id));

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer materialId;

    private String name;
    private String unit;
    private Integer price;
}
