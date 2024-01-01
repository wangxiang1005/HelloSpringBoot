package com.paladin.base.agent;

import java.lang.instrument.Instrumentation;

public class AgentDemo {

    /**
     * JVM 首先尝试在代理类上调用以下方法
     * 该方法在main方法之前运行
     * 与main方法运行在同一个JVM中
     *
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========premain方法执行-1========");
        System.out.println("agentArgs:="+agentArgs);
    }

    /**
     * 候选的、兜底方法：
     * 如果不存在 premain(String agentArgs, Instrumentation inst)则会执行premain(String agentArgs)
     *
     */
    public static void premain(String agentArgs) {
        System.out.println("=========premain方法执行-2========");
        System.out.println("agentArgs:="+agentArgs);
    }
}