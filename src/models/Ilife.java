package models;

import java.util.concurrent.ConcurrentLinkedDeque;

public interface Ilife {

    ConcurrentLinkedDeque<Person> getPeople();

    ConcurrentLinkedDeque<Food> getFood();

    int getYears();
}
