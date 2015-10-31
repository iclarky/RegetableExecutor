package org.hjy.regetable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

	class B implements Callable{
		int a = -1;
		
		public void setInt(int b){
			System.out.println(this+"		setInt "+b);
			a = b;}

		@Override
		public Object call() throws Exception {
			// TODO Auto-generated method stub
			System.out.println(this+"	started");
			while(a == -1){
				Main.A.sleep();
			}
			System.out.println(this+"	finished");
			return a;
		}
	}


public class Main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.test();
		main = null;
	}
	
	BlockingQueue<String> queue = new LinkedBlockingDeque<String>();
	ThreadPoolExecutor executor = new ThreadPoolExecutorImpl(3, 20, 3, TimeUnit.SECONDS, queue);
//	Executor executor = Executors.newFixedThreadPool(5);
	Future<Object>[] futures = (Future<Object>[]) new FutureTask<?>[20];
//	String[] a = new String[2];
	java.util.concurrent.ThreadPoolExecutor e;
	
	private void addTask(){
		for(int i=0;i<20;i++){
			futures[i]=executor.submit(i+"",new B());
		}
		
	}
	
	private void getTask(){
		for(int i=0;i<15;i++){
			if(i%3 == 0){
				A.sleep();
			}
			((B)executor.get(i+"")).setInt(i*2);
		}
	}
	
	private void test() {
		// TODO Auto-generated method stub
		executor.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("hello");
				while(true){
					System.out.println(executor);
					A.sleep();
				}
			}});
		executor.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					for(int i=0;i<20;i++){
						Object result = null;
						try {
							 result = futures[i] == null ? null:futures[i].get();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(result != null){
							System.out.println("			get result = "+result +"	from "+futures[i]);
						}
					}
				}
			}});
		addTask();
		getTask();
//		executor.shutdown();
		System.out.println("end test");
	}
	
	static class A implements Runnable{
		String a;
		A(String b){a = b;}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			sleep();
			System.out.println(a);
			sleep();
		}
		
		static void sleep(){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
