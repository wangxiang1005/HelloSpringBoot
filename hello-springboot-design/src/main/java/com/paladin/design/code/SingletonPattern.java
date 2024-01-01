package com.paladin.design.code;

/**
 * 单例模式
 */
public class SingletonPattern {

    private static SingletonPattern instance = null;

    private SingletonPattern() {
        // 初始化操作
    }

    //线程不安全
    public static SingletonPattern getInstance2() {
        if (instance == null) {
            // 如果实例为空,则创建一个新实例
            instance = new SingletonPattern();
        }
        return instance;
    }

    //提供get方法以供外界获取单例-线程安全,双重校验锁
    public static SingletonPattern getInstance() throws Exception{
        if(instance == null){
            synchronized (SingletonPattern.class) {
                if(instance == null){
                    instance = new SingletonPattern();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello, I am a Singleton!");
    }

    public static void main(String[] args) throws Exception {
        // 获取单例实例
        SingletonPattern singleton = SingletonPattern.getInstance();
        // 调用成员方法
        singleton.showMessage();
    }
}