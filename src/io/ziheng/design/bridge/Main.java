package io.ziheng.design.bridge;

/**
 * Structural Design Patterns - Bridge
 *
 *          ---- Shapes ----                 Color
 *         /                \         +     /     \
 * Rectangle(Color)      Cicle(Color)    Blue     Red
 *
 */
public class Main {
    public static void main(String[] args) {
        Color colorRed = new Color(Color.RED);
        Rectangle rectangleA = new Rectangle(colorRed);
        Cicle cicleA = new Cicle(colorRed);
        rectangleA.draw();
        cicleA.draw();
    }
}
/* EOF */