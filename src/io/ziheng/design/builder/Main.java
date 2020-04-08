package io.ziheng.design.builder;

import io.ziheng.design.builder.User;

public class Main {
    public static void main(String[] args) {
        User userA = User.getUserBuilder()
                    .age(22)
                    .phone("phoneA")
                    .address("addressA")
                    .build();

        User userB = User.getUserBuilder()
                    .age(12)
                    .phone("phoneB")
                    .phone("addressB")
                    .build();
    }
}
/* EOF */