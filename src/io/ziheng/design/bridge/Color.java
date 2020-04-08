package io.ziheng.design.bridge;
public class Color {

    public static final int NO_COLOR = -1;
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int RED = 2;
    public static final int BLUE = 3;

    private int color;

    public Color(int color) {
        this.color = color;
    }

    public String getColor() {
        switch (color) {
            case WHITE: return "White";
            case BLACK: return "Black";
            case RED: return "Red";
            case BLUE: return "Blue";
            default: return "No Color";
        }
    }

    public void setColor(int color) {
        this.color = color;
    }

}
/* EOF */