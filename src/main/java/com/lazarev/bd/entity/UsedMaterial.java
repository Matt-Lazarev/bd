package com.lazarev.bd.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Accessors(chain = true)

@Entity
@Table(name = "used_material")
public class UsedMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usedMaterialId;

    private Integer amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "objectId")
    private Object object;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "materialId")
    private Material material;
}
