package io.ziheng.design.singleton;
public class SingletonWithInnerClass {
    private static class SingletonHolder {
        private static final SingletonWithInnerClass INSTANCE = new SingletonWithInnerClass();
    }
    private SingletonWithInnerClass() {
        // ...
    }
    public static SingletonWithInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
/* EOF */