package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class ManagerRepository {
    private final JdbcTemplate template;

    public void updateManagerFirstnameById(Integer id, String newName){
        String sql = "update manager set firstname = ? where manager_id = ?";
        template.update(sql, newName, id);
    }

    public void updateManagerLastnameById(Integer id, String newName){
        String sql = "update manager set lastname = ? where manager_id = ?";
        template.update(sql, newName, id);
    }

    public void updateManagePhoneNumberById(Integer id, String newName){
        String sql = "update manager set phone_number = ? where manager_id = ?";
        template.update(sql, newName, id);
    }
}
