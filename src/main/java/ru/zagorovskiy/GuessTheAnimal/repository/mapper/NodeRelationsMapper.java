package ru.zagorovskiy.GuessTheAnimal.repository.mapper;

import ru.zagorovskiy.GuessTheAnimal.entiti.NodeRelations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NodeRelationsMapper implements RowMapper<NodeRelations> {
    @Override
    public NodeRelations mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new NodeRelations(
                rs.getLong("parent_id"),
                rs.getLong("yes_child"),
                rs.getLong("no_child")
        );
    }
}
