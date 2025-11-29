package com.github.joel003.model;

public class BodyFat {

    private String gender;
    private double hipSize;
    private double height;
    private double waistSize;
    private double neckSize;

    public BodyFat() {
    }

    public BodyFat(String gender, double hipSize, double height, double waistSize, double neckSize) {
        this.gender = gender;
        this.hipSize = hipSize;
        this.height = height;
        this.waistSize = waistSize;
        this.neckSize = neckSize;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHipSize() {
        return hipSize;
    }

    public void setHipSize(double hipSize) {
        this.hipSize = hipSize;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(double waistSize) {
        this.waistSize = waistSize;
    }

    public double getNeckSize() {
        return neckSize;
    }

    public void setNeckSize(double neckSize) {
        this.neckSize = neckSize;
    }
}
