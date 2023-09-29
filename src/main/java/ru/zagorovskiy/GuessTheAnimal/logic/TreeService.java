package ru.zagorovskiy.GuessTheAnimal.logic;

import org.springframework.stereotype.Service;
import ru.zagorovskiy.GuessTheAnimal.entiti.Node;
import ru.zagorovskiy.GuessTheAnimal.entiti.NodeRelations;
import ru.zagorovskiy.GuessTheAnimal.entiti.Tree;
import ru.zagorovskiy.GuessTheAnimal.repository.NodeRepository;
import ru.zagorovskiy.GuessTheAnimal.repository.RelationRepository;

import java.util.*;

@Service
public class TreeService {
    private final RelationRepository relationRepository;
    private final NodeRepository nodeRepository;

    public TreeService(RelationRepository relationRepository, NodeRepository nodeRepository) {
        this.relationRepository = relationRepository;
        this.nodeRepository = nodeRepository;
    }


    public Tree buildTree() {
        List<NodeRelations> nodeRelations = relationRepository.getAllRelations();
        List<Node> nodes = nodeRepository.getAllNodes();

        Map<Long, Node> treeNodeMap = new HashMap<>();

        for (Node node : nodes) {
            treeNodeMap.put(node.getId(), node);
        }

        // Строим связи между узлами
        for (NodeRelations relation : nodeRelations) {
            Node parent = treeNodeMap.get(relation.getParentId());
            Node yesChild = treeNodeMap.get(relation.getYesChildId());
            Node noChild = treeNodeMap.get(relation.getNoChildId());

            if (parent != null) {
                parent.setYesChild(yesChild);
                parent.setNoChild(noChild);
            }
        }


        Node rootNode = treeNodeMap.get(1L);
        buildTreeRecursively(rootNode, treeNodeMap);

        return new Tree(rootNode);
    }

    private void buildTreeRecursively(Node currentTreeNode, Map<Long, Node> treeNodeMap) {
        if (currentTreeNode == null) {
            return;
        }
        Node yesChild = currentTreeNode.getYesChild();
        Node noChild = currentTreeNode.getNoChild();

        if (yesChild != null) {
            currentTreeNode.setYesChild(treeNodeMap.get(yesChild.getId()));
            buildTreeRecursively(currentTreeNode.getYesChild(), treeNodeMap);
        }

        if (noChild != null) {
            currentTreeNode.setNoChild(treeNodeMap.get(noChild.getId()));
            buildTreeRecursively(currentTreeNode.getNoChild(), treeNodeMap);
        }
    }

    public void playTheGame(Node rootNode) {
        Scanner in = new Scanner(System.in);
        System.out.println("Загадай животное, а я попробую угадать...");
        String playAgain = "да";
        while (playAgain.equals("да")) {
            Node currentNode = rootNode;
            while (currentNode != null && !currentNode.isLeaf()) {
                System.out.println("Это животное " + currentNode.getQuestion() + "? (да/нет)");
                String choice = in.next();
                if (choice.equals("да")) {
                    currentNode = currentNode.getYesChild();
                } else {
                    currentNode = currentNode.getNoChild();
                }
            }
            if (currentNode != null && currentNode.isLeaf()) {
                System.out.println("Это " + currentNode.getAnimalName() + "? (да/нет)");
                String choice = in.next();
                in.nextLine();
                if (choice.equals("да")) {
                    System.out.println("Ура, победа!");
                } else {
                    System.out.println("Какое животное ты загадал?");
                    String newAnimal = in.nextLine();

                    System.out.println("Чем \"" + newAnimal + "\" отличается от \""
                            + currentNode.getAnimalName() + "\"?");
                    String newQuestion = in.nextLine();

                    updateDataBase(currentNode, newAnimal, newQuestion);
                    updateTree(currentNode, newAnimal, newQuestion);
                }

                System.out.println("Хотите сыграть снова? (да/нет)");
                playAgain = in.next();
            }
        }
    }

    private void updateDataBase(Node currentNode, String newAnimal, String newQuestion) {
        nodeRepository.updateAnimalNodeOnQuestionNode(currentNode.getAnimalName(), newQuestion);
        nodeRepository.create(newAnimal);
        nodeRepository.create(currentNode.getAnimalName());

        relationRepository.create(newQuestion, newAnimal, currentNode.getAnimalName());
    }


    private void updateTree(Node cur, String newAnimal, String newQuestion) {
        cur.setLeaf(false);
        cur.setQuestion(newQuestion);
        cur.setYesChild(new Node(
                null,
                null,
                newAnimal,
                true,
                null, null)
        );
        cur.setNoChild(new Node(
                null,
                null,
                cur.getAnimalName(),
                true,
                null, null)
        );
        cur.setAnimalName(null);
    }


}
