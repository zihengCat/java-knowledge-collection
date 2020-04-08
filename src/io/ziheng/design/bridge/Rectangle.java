package io.ziheng.design.bridge;

public class Rectangle extends Shape {

    private Color color;

    public Rectangle(Color color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println(
            String.format(
                "Rectangle(%s).draw()",
                color.getColor()
            )
        );
    }
}
/* EOF */