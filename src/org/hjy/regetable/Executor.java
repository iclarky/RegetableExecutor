package org.hjy.regetable;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author hunter
 *
 */
public interface Executor extends java.util.concurrent.Executor{

	/**
	 * execute runnable in Executor.
	 * @param key used to identify the runnable.
	 * @param command the runnable wanted to be executed.
	 * @throws RejectedExecutionException  if the given runnable cannot be accepted for execution.
	 * @throws NullPointerException if key or command is null.
	 */
	void execute(String key, Runnable command) throws RejectedExecutionException,NullPointerException;
	
	/**
	 * Submits a value-returning task for execution and returns a Future representing the pending results of the task.
	 * @param key used to identify the runnable.
	 * @param task the callable wanted to be executed.
	 * @return a Future representing pending completion of the task.
	 * @throws RejectedExecutionException if the task cannot be scheduled for execution.
	 * @throws NullPointerException if key or task is null.
	 */
	<T> Future<T> submit(String key, Callable<T> task) throws RejectedExecutionException,NullPointerException;
	
	/**
	 * get the correspond Runnable or Callable that have been executed or submited.
	 * @param key
	 * @return the correspond Runnable or Callable, may be null if it have finished.
	 * @throws NullPointerException
	 */
	Object get(String key) throws NullPointerException;
}
