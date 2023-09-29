package ru.zagorovskiy.GuessTheAnimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.zagorovskiy.GuessTheAnimal.entiti.Tree;
import ru.zagorovskiy.GuessTheAnimal.logic.TreeService;

@SpringBootApplication
public class GuessTheAnimalApplication implements CommandLineRunner {
    @Autowired
    private TreeService treeService;

    public static void main(String[] args) {
        SpringApplication.run(GuessTheAnimalApplication.class, args);

    }

    public void run(String... args) {
        Tree tree = treeService.buildTree();
        treeService.playTheGame(tree.getRoot());
    }
}
