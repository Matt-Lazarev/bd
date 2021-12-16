package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class ProjectRepository {
    private final JdbcTemplate template;

    public void updateProjectDescriptionById(Integer id, String newValue){
        String sql = "update project set description = ? where project_id = ?";
        template.update(sql, newValue, id);
    }

    public void updateProjectWorkStartAtById(Integer id, LocalDate newValue){
        String sql = "update project set work_start_at = ? where project_id = ?";
        template.update(sql, newValue, id);
    }

    public void updateProjectWorkEndAtById(Integer id, LocalDate newValue){
        String sql = "update project set work_end_at = ? where project_id = ?";
        template.update(sql, newValue, id);
    }


}
