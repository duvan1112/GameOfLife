package models;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Life extends Thread implements Ilife {
    private final ConcurrentLinkedDeque<Person> people;
    private final ConcurrentLinkedDeque<Food> foods;
    private final int width;
    private final int height;
    private int years;
    private final Random random;

    public Life(int men, int women, int width, int height) {
        random = new Random();
        years = 1;
        this.width = width;
        this.height = height;
        this.people = new ConcurrentLinkedDeque<>();
        this.foods = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < men; i++) {
            people.add(new Person(random.nextInt(width - 10), random.nextInt(height - 10), 0, random.nextInt(80)));
        }
        for (int i = 0; i < women; i++) {
            people.add(new Person(random.nextInt(width - 10), random.nextInt(height - 10), 1, random.nextInt(80)));
        }
        generateNewFood(height, width);
    }

    @Override
    public void run() {
        while (people.size() != 0) {
            movePeople();
            checkCollisions();
            checkOldAgeDeath();
            checkFoodExpiration();
            generateNewFood(height, width);
            years++;
            if (people.size()<1000){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void generateNewFood(int height, int width) {
        for (int i = 0; i < (height + width); i++) {
            foods.add(new Food(random.nextInt(width - 10), random.nextInt(height - 10)));
        }
    }

    private void checkFoodExpiration() {
        for (Food food : foods) {
            food.newExpiration();
            if (food.getExpiration() <= 0) {
                foods.remove(food);
            }
        }
    }

    private void checkOldAgeDeath() {
        for (Person person : people) {
            if (person.getAge() > 90) {
                people.remove(person);
            }
            person.setAge();
        }
    }

    private void checkCollisions() {
        for (Person person : people) {
            for (Person person1 : people) {
                if (!person.equals(person1)) {
                    if (person.intersects(person1) && person.getGender() == 0 && person1.getGender() == 0 && person.getAge() < 15 && person1.getAge() < 15) {
                        boolean b = random.nextBoolean();
                        if (b) {
                            people.remove(person);
                        } else {
                            people.remove(person1);
                        }
                    }
                    if (person.intersects(person1) && person.getGender() == 0 && person1.getGender() == 0 && person.getAge() < 15 && person1.getAge() > 14) {
                        if (person.getEnergy() > person1.getEnergy()) {
                            people.remove(person);
                        } else {
                            people.remove(person1);
                        }
                    }
                    if (person.intersects(person1) && person.getGender() == 0 && person1.getGender() == 1 && person.getAge() >= 15) {
                        people.add(new Person(random.nextInt(width - 10), random.nextInt(height - 10), random.nextInt(2)));
                    }

                }

            }
            for (Food food : foods) {
                if (person.intersects(food)) {
                    person.addEnergy();
                    foods.remove(food);
                }
            }
            if (person.getEnergy() < 0) {
                people.remove(person);
            }
            person.lessEnergy();
        }

    }


    private void movePeople() {
        for (Person person : people) {
            int speed = speed(person);
            int vertical;
            int horizontal;
            if (random.nextBoolean()) {
                vertical = speed;
            } else {
                vertical = -speed;
            }
            if (random.nextBoolean()) {
                horizontal = speed;
            } else {
                horizontal = -speed;
            }
            if (person.getX() + horizontal < width && person.getX() + horizontal >= 0) {
                person.x += horizontal;
            }
            if (person.getY() + vertical < height && person.getY() + vertical >= 10) {
                person.y += vertical;
            }
        }
    }

    private int speed(Person person) {
        if (person.getAge() >= 0 && person.getAge() < 5) {
            return 1;
        } else if (person.getAge() >= 5 && person.getAge() < 14) {
            return 5;
        } else if (person.getAge() >= 14 && person.getAge() < 60) {
            return 4;
        } else {
            return 2;
        }
    }


    @Override
    public ConcurrentLinkedDeque<Person> getPeople() {
        return people;
    }

    @Override
    public ConcurrentLinkedDeque<Food> getFood() {
        return foods;
    }

    @Override
    public int getYears() {
        return years;
    }

}
