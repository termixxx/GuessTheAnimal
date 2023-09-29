package ru.zagorovskiy.GuessTheAnimal.entiti;

public class NodeRelations {
    private Long parentId;
    private Long yesChildId;
    private Long noChildId;

    public NodeRelations(Long parentId, Long yesChildId, Long noChildId) {
        this.parentId = parentId;
        this.yesChildId = yesChildId;
        this.noChildId = noChildId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getYesChildId() {
        return yesChildId;
    }

    public void setYesChildId(Long yesChildId) {
        this.yesChildId = yesChildId;
    }

    public Long getNoChildId() {
        return noChildId;
    }

    public void setNoChildId(Long noChildId) {
        this.noChildId = noChildId;
    }
}
