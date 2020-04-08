package io.ziheng.design.bridge;

public class Cicle extends Shape {

    private Color color;

    public Cicle(Color color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println(
            String.format(
                "Cicle(%s).draw()",
                color.getColor()
            )
        );
    }
}
/* EOF */