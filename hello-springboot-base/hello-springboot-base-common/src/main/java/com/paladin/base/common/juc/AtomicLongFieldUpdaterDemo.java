package com.paladin.base.common.juc;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class AtomicLongFieldUpdaterDemo {
	
	private volatile long value;

	private static final AtomicLongFieldUpdater<AtomicLongFieldUpdaterDemo> LONG_FIELD_UPDATER = AtomicLongFieldUpdater.newUpdater(AtomicLongFieldUpdaterDemo.class, "value");

	public static void main(String[] args) {
		AtomicLongFieldUpdaterDemo demo = new AtomicLongFieldUpdaterDemo();
		demo.value = 10;
		LONG_FIELD_UPDATER.compareAndSet(demo, 10, 20);
	}
}