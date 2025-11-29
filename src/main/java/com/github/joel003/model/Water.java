package com.github.joel003.model;

public class Water {

    private double weight;
    private double category;

    public Water() {
    }

    public Water(double weight, double category) {
        this.weight = weight;
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCategory() {
        return category;
    }

    public void setCategory(double category) {
        this.category = category;
    }
}
