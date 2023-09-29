package ru.zagorovskiy.GuessTheAnimal.entiti;


public class Node {
    private Long id;
    private String question;
    private String animalName;
    private boolean isLeaf;
    private Node yesChild;
    private Node noChild;

    public Node(Long id, String question, String animalName, boolean isLeaf, Node yesChild, Node noChild) {
        this.id = id;
        this.question = question;
        this.animalName = animalName;
        this.isLeaf = isLeaf;
        this.yesChild = yesChild;
        this.noChild = noChild;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public Node getYesChild() {
        return yesChild;
    }

    public void setYesChild(Node yesChild) {
        this.yesChild = yesChild;
    }

    public Node getNoChild() {
        return noChild;
    }

    public void setNoChild(Node noChild) {
        this.noChild = noChild;
    }
}
