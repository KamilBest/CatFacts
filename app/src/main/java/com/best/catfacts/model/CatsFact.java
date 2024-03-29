package com.best.catfacts.model;

public class CatsFact {

    private String fact;
    private Long length;
    private boolean isSelected = false;

    /**
     * No args constructor for use in serialization
     */
    public CatsFact() {
    }

    /**
     * @param fact
     * @param length
     */
    public CatsFact(String fact, Long length) {
        super();
        this.fact = fact;
        this.length = length;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "CatsFact{" +
                "fact='" + fact + '\'' +
                ", length=" + length +
                '}';
    }
}
