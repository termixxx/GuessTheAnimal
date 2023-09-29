package ru.zagorovskiy.GuessTheAnimal.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.zagorovskiy.GuessTheAnimal.entiti.Node;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NodeMapper implements RowMapper<Node> {
    @Override
    public Node mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Node(
                rs.getLong("node_id"),
                rs.getString("question_text"),
                rs.getString("animal_name"),
                rs.getBoolean("is_leaf"),
                null,
                null
        );
    }
}
