package com.reyes.securityr.controller;

public class ThreadDemo {
	
	/*** 基本測試
	Object lock1 = new Object();
	Object lock2 = new Object();
	
	public void func1 () {
		synchronized (lock1) {
			func2();
		}
	}
	
	public void func2 () {
		synchronized (lock2) {
			while (true) {
				System.out.println("");
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadDemo demo = new ThreadDemo();
		demo.func1();
	}***/
	
	public static void main(String[] args) {
		Object sharedLock = new Object();
		Object ownLock = new Object();
		
		/** Thread 1: 持有 sharedLock 並 sleep → TIMED_WAITING */
		new Thread(()-> {
			synchronized (sharedLock) {
				try {
					Thread.sleep(20000);
				} catch (Exception e) {
				}
			}
		}, "Thread-Locked").start();
		
			
		/** Thread 2: 持有 ownLock 並嘗試取得 sharedLock → BLOCKED */
		new Thread(()-> {
			synchronized (sharedLock) {
				try {
					Thread.sleep(20000);
				} catch (Exception e) {
				}
			}
		}, "Thread-WaitingTo").start();
		
		/** Thread 3: 在 ownLock.wait() → TIMED_WAITING (on object monitor) */
		new Thread(()-> {
			synchronized (ownLock) {
				try {
					ownLock.wait(20000);
				} catch (Exception e) {
				}
			}
		}, "Thread-WaitingOn").start();
		
	}
}
