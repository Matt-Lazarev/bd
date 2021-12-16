package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class ContractRepository {
    private final JdbcTemplate template;

    public void updateContractNameById(Integer id, String newName){
        String sql = "update contract set name = ? where contract_id = ?";
        template.update(sql, newName, id);
    }

    public void updateContractConclusionAtById(Integer id, LocalDate newDate){
        String sql = "update contract set conclusion_at = ? where contract_id = ?";
        template.update(sql, newDate, id);
    }
}
