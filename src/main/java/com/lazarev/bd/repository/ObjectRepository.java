package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ObjectRepository {

    private final JdbcTemplate template;

    public void updateObjectNameById(Integer id, String newName){
        String sql = "update object set name = ? where object_id = ?";
        template.update(sql, newName, id);
    }

    public void updateObjectDescriptionById(Integer id, String newName){
        String sql = "update object set description = ? where object_id = ?";
        template.update(sql, newName, id);
    }

}
