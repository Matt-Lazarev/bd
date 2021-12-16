package com.lazarev.bd.entity;

//                                object_address_id INT NOT NULL AUTO_INCREMENT,
//                                city VARCHAR(30) NOT NULL,
//                                street VARCHAR(30) NOT NULL,
//                                house INT NOT NULL,
//                                object_id INT NOT NULL,
//                                PRIMARY KEY (object_address_id),
//                                FOREIGN KEY (object_id) REFERENCES object (object_id));

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "object_address")
public class ObjectAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objectAddressId;

    private String city;
    private String street;
    private Integer house;

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "objectId")
    private Object object;
}
