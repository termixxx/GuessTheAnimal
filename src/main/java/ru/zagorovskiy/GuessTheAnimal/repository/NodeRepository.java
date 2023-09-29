package ru.zagorovskiy.GuessTheAnimal.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zagorovskiy.GuessTheAnimal.entiti.Node;
import ru.zagorovskiy.GuessTheAnimal.repository.mapper.NodeMapper;

import java.util.List;

@Repository
public class NodeRepository {
    private final JdbcTemplate jdbcTemplate;

    public NodeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Node> getAllNodes() {
        String query = "SELECT * FROM node";
        return jdbcTemplate.query(query, new NodeMapper());
    }

    public void updateAnimalNodeOnQuestionNode(String animalName, String newQuestion) {
        String query = "UPDATE node SET question_text = ?, animal_name = null, is_leaf = false " +
                "WHERE animal_name = ?";
        jdbcTemplate.update(query, newQuestion, animalName);
    }

    public void create(String animalName) {
        String query = "INSERT INTO node (question_text, animal_name, is_leaf)" +
                " VALUES (null, ?, true)";
        jdbcTemplate.update(query, animalName);
    }

}
