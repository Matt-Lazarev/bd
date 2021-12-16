package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BrigadeRepository {
    private final JdbcTemplate template;

    public void updateBrigadeNameById(Integer id, String newName){
        String sql = "update brigade set name = ? where brigade_id = ?";
        template.update(sql, newName, id);
    }

}
