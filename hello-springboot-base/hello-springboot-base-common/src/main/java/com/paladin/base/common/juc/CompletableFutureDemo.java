package com.paladin.base.common.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
	public static void main(String[] args) throws Exception{
		CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 1;
		});
		CompletableFuture.runAsync(() -> {
			System.out.println("提交异步任务");
		});
		System.out.println("获取异步任务的结果:" + supplyAsync.get());
	}
}