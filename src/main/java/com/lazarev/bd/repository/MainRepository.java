package com.lazarev.bd.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class MainRepository {
    private static final String SELECT_SQL = "select * from TABLE";
    private static final String SELECT_BY_ID_SQL = "select * from TABLE where TABLE_id = OBJECT_ID";
    private static final String SELECT_ORDER_BY_SQL = "select * from TABLE order by FIELD RESULT_ORDER";
    private static final String SELECT_LIKE_SQL = "select * from TABLE where FIELD like 'LIKE_VALUE'";
    private static final String SELECT_BETWEEN_SQL = "select * from TABLE where FIELD between FROM_VALUE and TO_VALUE";
    private static final String INSERT_SQL = "insert into TABLE values (null, VALUES_PARAMS)";
    private static final String DELETE_BY_ID_SQL = "delete from TABLE where TABLE_id = OBJECT_ID";

    private static final String FILTER_ONE_FIELD_SQL = "select * from TABLE where FIELD1 COMP1 ?";
    private static final String FILTER_TWO_FIELDS_SQL =
            "select * from TABLE where FIELD1 COMP1 ? LOG_SIGN1 FIELD2 COMP2 ?";
    private static final String FILTER_THREE_FIELDS_SQL =
            "select * from TABLE where FIELD1 COMP1 ? LOG_SIGN1 FIELD2 COMP2 ? LOG_SIGN2 FIELD3 COMP3 ?";

    private final JdbcTemplate template;

    public List<Map<String, Object>> getAllObjects(String table){
        String sql = SELECT_SQL.replace("TABLE", table);
        return template.queryForList(sql);
    }

    public List<Map<String, Object>> getObjectById(String table, String id){
        String sql = SELECT_BY_ID_SQL
                .replace("TABLE", table)
                .replace("OBJECT_ID", id);
        return template.queryForList(sql);
    }

    public List<Map<String, Object>> getAllObjectsOrderBy(String table, String field, String order){
        String sql = SELECT_ORDER_BY_SQL
                .replace("TABLE", table)
                .replace("FIELD", field)
                .replace("RESULT_ORDER", order);
        return template.queryForList(sql);
    }

    public List<Map<String, Object>> getAllObjectsLike(String table, String field, String likeExpression){
        String sql = SELECT_LIKE_SQL
                .replace("TABLE", table)
                .replace("FIELD", field)
                .replace("LIKE_VALUE", likeExpression);
        return template.queryForList(sql);
    }

    public List<Map<String, Object>> getAllObjectsBetween(String table, String field, String from, String to){
        String sql = SELECT_BETWEEN_SQL
                .replace("TABLE", table)
                .replace("FIELD", field)
                .replace("FROM_VALUE", from)
                .replace("TO_VALUE", to);
        return template.queryForList(sql);
    }

    public void insert(String table, String[] params) {
        int len = params.length;
        String count;

        if(len == 2){
            count = "?, ?";
        }
        else if (len == 3){
            count = "?, ?, ?";
        }
        else {
            count = "?, ?, ?, ?";
        }

        String sql = INSERT_SQL
                .replace("TABLE", table)
                .replace("VALUES_PARAMS", count);

        if(len == 2){
            template.update(sql, params[0], params[1]);
        }
        else if (len == 3){
            template.update(sql, params[0], params[1], params[2]);
        }
        else {
            template.update(sql, params[0], params[1], params[2], params[3]);
        }
    }

    public void deleteById(String table, String id) {
        String sql = DELETE_BY_ID_SQL
                .replace("TABLE", table)
                .replace("OBJECT_ID", id);
        template.update(sql);
    }

    public List<Map<String, Object>> getFilteredObjectsOneFilter(String table, String f1, String c1, String v1){
        String sql = FILTER_ONE_FIELD_SQL
                .replace("TABLE", table)
                .replace("FIELD1", f1)
                .replace("COMP1", c1);
        return template.queryForList(sql, v1);
    }

    public List<Map<String, Object>> getFilteredObjectsTwoFilters(String table,
                                                                  String f1, String c1, String v1, String f2, String c2, String v2, String ls1){
        String sql = FILTER_TWO_FIELDS_SQL
                .replace("TABLE", table)
                .replace("FIELD1", f1)
                .replace("COMP1", c1)
                .replace("FIELD2", f2)
                .replace("COMP2", c2)
                .replace("LOG_SIGN1", ls1);
        return template.queryForList(sql, v1, v2);
    }

    public List<Map<String, Object>> getFilteredObjectsThreeFilters(String table,
                                                                    String f1, String c1, String v1, String f2, String c2, String v2, String f3, String c3, String v3, String ls1, String ls2){
        String sql = FILTER_THREE_FIELDS_SQL
                .replace("TABLE", table)
                .replace("FIELD1", f1)
                .replace("COMP1", c1)
                .replace("FIELD2", f2)
                .replace("COMP2", c2)
                .replace("FIELD3", f3)
                .replace("COMP3", c3)
                .replace("LOG_SIGN1", ls1)
                .replace("LOG_SIGN2", ls2);
        return template.queryForList(sql, v1, v2, v3);
    }
}
