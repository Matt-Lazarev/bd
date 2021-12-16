package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MaterialRepository {
    private final JdbcTemplate template;

    public void updateMaterialNameById(Integer id, String newName){
        String sql = "update material set name = ? where material_id = ?";
        template.update(sql, newName, id);
    }

    public void updateMaterialUnitById(Integer id, String newName){
        String sql = "update material set unit = ? where material_id = ?";
        template.update(sql, newName, id);
    }

    public void updateMaterialPriceById(Integer id, Integer newName){
        String sql = "update material set price = ? where material_id = ?";
        template.update(sql, newName, id);
    }
}
