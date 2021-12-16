package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class UsedMaterialRepository {
    private final JdbcTemplate template;

    public void updateUsedMaterialAmountById(Integer id, Integer newValue){
        String sql = "update used_material set amount = ? where used_material_id = ?";
        template.update(sql, newValue, id);
    }

    public void updateUsedMaterialDateById(Integer id, LocalDate newValue){
        String sql = "update used_material set date = ? where used_material_id = ?";
        template.update(sql, newValue, id);
    }

}
