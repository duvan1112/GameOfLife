package models;

import java.awt.*;

public class Person extends Rectangle {
    private int energy;
    private final int gender;
    private int age;
    public Person(int posX, int posY, int gender) {
        super(posX, posY, 10, 10);
        this.energy = 5;
        this.gender = gender;
        age = 1;
    }
    public Person(int posX, int posY, int gender, int age) {
        super(posX, posY, 10, 10);
        this.energy = 10;
        this.gender = gender;
        this.age = age;

    }

    public int getEnergy() {
        return energy;
    }

    public void lessEnergy() {
        this.energy--;
    }

    public void addEnergy() {
        this.energy++;
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age++;
    }
}
