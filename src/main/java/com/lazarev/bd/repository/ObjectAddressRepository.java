package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ObjectAddressRepository {
    private final JdbcTemplate template;

    public void updateObjectAddressCityById(Integer id, String newValue){
        String sql = "update object_address set city = ? where object_address_id = ?";
        template.update(sql, newValue, id);
    }

    public void updateObjectAddressStreetById(Integer id, String newValue){
        String sql = "update object_address set street = ? where object_address_id = ?";
        template.update(sql, newValue, id);
    }

    public void updateObjectAddressHouseById(Integer id, Integer newValue){
        String sql = "update object_address set house = ? where object_address_id = ?";
        template.update(sql, newValue, id);
    }
}
