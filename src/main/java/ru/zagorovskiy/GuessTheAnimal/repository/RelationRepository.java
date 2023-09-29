package ru.zagorovskiy.GuessTheAnimal.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.GuessTheAnimal.entiti.NodeRelations;
import ru.zagorovskiy.GuessTheAnimal.repository.mapper.NodeRelationsMapper;

import java.util.List;

@Repository
public class RelationRepository {

    private final JdbcTemplate jdbcTemplate;

    public RelationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NodeRelations> getAllRelations() {
        String query = "SELECT * " +
                "FROM node_relations";
        return jdbcTemplate.query(query, new NodeRelationsMapper());
    }

    public void create(String newQuestion, String newAnimal, String oldAnimal) {
        String query = "INSERT INTO node_relations (parent_id, yes_child, no_child) " +
                "VALUES (" +
                "(SELECT node_id FROM node WHERE question_text = ?), " +
                "(SELECT node_id FROM node WHERE animal_name = ?), " +
                "(SELECT node_id FROM node WHERE animal_name = ?))";
        jdbcTemplate.update(query, newQuestion, newAnimal, oldAnimal);
    }
}
