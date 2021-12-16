package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ClientRepository {
    private final JdbcTemplate template;

    public void updateClientFirstnameById(Integer id, String newName){
        String sql = "update client set firstname = ? where client_id = ?";
        template.update(sql, newName, id);
    }

    public void updateClientLastnameById(Integer id, String newName){
        String sql = "update client set lastname = ? where client_id = ?";
        template.update(sql, newName, id);
    }

    public void updateClientPaymentAccountById(Integer id, String newName){
        String sql = "update client set payment_account = ? where client_id = ?";
        template.update(sql, newName, id);
    }
}
