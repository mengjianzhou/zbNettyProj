package com.robert.thread.join;

import java.util.concurrent.TimeUnit;

public class JoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				System.out.println("current Thread t1!");
			}
		});
		Thread t2 = new Thread(new MyThread(t1,"T2"));
		Thread t3 = new Thread(new MyThread(t2,"T3"));
		Thread t4 = new Thread(new MyThread(t3,"T4"));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		
		
		System.out.println("hello ");
		TimeUnit.SECONDS.sleep(5);
	}
	
	static class MyThread implements Runnable{
		
		private Thread previousThread;
		private String name;
		
		MyThread(Thread thread,String name){
			previousThread = thread;
			this.name = name;
		}

		public void run() {
			try {
				previousThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread:" +name);
		}
		
	}
	
	
}
