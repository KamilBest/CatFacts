package com.best.catfacts.model;

public class CatsFacts {

    private String fact;
    private Long length;

    /**
     * No args constructor for use in serialization
     */
    public CatsFacts() {
    }

    /**
     * @param fact
     * @param length
     */
    public CatsFacts(String fact, Long length) {
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
}
