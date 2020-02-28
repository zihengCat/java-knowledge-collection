package io.ziheng.design.singleton;
public class SingletonWithStatic {
    private static final SingletonWithStatic INSTANCE = new SingletonWithStatic();
    private SingletonWithStatic() {
        // ...
    }
    public static SingletonWithStatic getInstance() {
        return INSTANCE;
    }
}
/* EOF */