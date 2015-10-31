package org.hjy.regetable;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


public interface ThreadPoolExecutor extends Executor {
	
	 class Task {
		 
		final String key;
		final Runnable runnable;
		final Callable callable;
		
		public Task(String key, Runnable runnable, Callable callable) {
			if(key == null || runnable == null){
				throw new NullPointerException();
			}
			this.key = key;
			this.runnable = runnable;
			this.callable = callable;
		}

		@Override
		public String toString() {
			return "Task [key=" + key + ", runnable=" + runnable
					+ ", callable=" + callable + "]";
		}
		
	}
	 
	abstract class BRejectedExecutionHandler implements RejectedExecutionHandler{
		
		public abstract void rejectedExecution(Task task, ThreadPoolExecutor e);
		
		@Override
		public void rejectedExecution(Runnable r,
				java.util.concurrent.ThreadPoolExecutor executor) {
		}
	}
	
	void shutdown();
	
	List<Task> shutdownNow();
	
	boolean isShutdown();
	
	boolean isTerminating();
	
	boolean isTerminated();
	
	boolean awaitTermination(long timeout, TimeUnit unit)
	        throws InterruptedException;
	
	void setThreadFactory(ThreadFactory threadFactory);
	
	ThreadFactory getThreadFactory();
	
	void setRejectedExecutionHandler(BRejectedExecutionHandler handler);
	
	BRejectedExecutionHandler getRejectedExecutionHandler();
	
	void setCorePoolSize(int corePoolSize);
	
	int getCorePoolSize();
	
	boolean prestartCoreThread();
	
	int prestartAllCoreThreads();
	
	boolean allowsCoreThreadTimeOut();
	
	void allowCoreThreadTimeOut(boolean value);
	
	void setMaximumPoolSize(int maximumPoolSize);
	
	int getMaximumPoolSize();
	
	void setKeepAliveTime(long time, TimeUnit unit);
	
	long getKeepAliveTime(TimeUnit unit);
	
	BlockingQueue<String> getQueue();
	
	boolean remove(Task task);
	
	void purge();
	
	int getPoolSize();
	
	int getActiveCount();
	
	int getLargestPoolSize();
	
	long getTaskCount();
	
	long getCompletedTaskCount();
	
	<T> Future<T> execute(Task task) throws RejectedExecutionException,NullPointerException;
	
}
