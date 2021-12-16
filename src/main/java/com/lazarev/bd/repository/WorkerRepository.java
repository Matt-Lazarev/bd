package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class WorkerRepository {
    private final JdbcTemplate template;

    public void updateWorkerFirstnameById(Integer id, String newValue){
        String sql = "update worker set firstname = ? where worker_id = ?";
        template.update(sql, newValue, id);
    }

    public void updateWorkerLastnameById(Integer id, String newValue){
        String sql = "update worker set lastname = ? where worker_id = ?";
        template.update(sql, newValue, id);
    }

}
