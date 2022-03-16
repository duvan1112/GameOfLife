package models;

import java.awt.*;

public class Food extends Rectangle {
    private int expiration;

    public Food(int x, int y) {
        super(x, y, 10, 10);
        expiration = 5;
    }

    public void newExpiration() {
        expiration--;
    }

    public int getExpiration() {
        return expiration;
    }
}
