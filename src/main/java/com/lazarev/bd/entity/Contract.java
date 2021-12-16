package com.lazarev.bd.entity;

//                          contract_id INT NOT NULL AUTO_INCREMENT,
//                          name VARCHAR(30) NOT NULL,
//                          conclusion_at DATE NOT NULL,
//                          manager_id INT NOT NULL,
//                          client_id INT NOT NULL,
//                          PRIMARY KEY (contract_id),
//                          FOREIGN KEY (manager_id) REFERENCES manager (manager_id),
//                          FOREIGN KEY (client_id) REFERENCES client (client_id));

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    private String name;
    private LocalDate conclusionAt;
}
