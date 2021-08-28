package Main;

import java.io.*;
import java.lang.*;
import java.util.*;

// Multithreading is a way of multitasking in which light weight sub-processes are created
// multithreading is better than multiprocessing because it uses a shared memory area and
// doesn't allocate new memory to new processes. Also context switching takes less time
// 
// Types of Multitasking : -
// 
// 1. Process based Multitasking : -
//		-> Each process requires a lot of memory, and every process creates a separate memory block.
// 2. Thread based Multitasking : -
// 		-> All threads share the same memory space, and also threads are lightwieght as compared to processes.
//
// 
// Thread Scheduler : -
// It decides which threads should run in the JVM.
// Types of Scheduling : -
// 		1. Time Slicing : Executes a task for a fixed amount of time, and then returns it to the task pool.
// 		2. Pre-emptive Scheduling : A higher priority task will be executed until it completes or a higher priority task comes into existence.
// 
// A thread cannot be ran twice.

// Daemon Thread : -
// 		- It is a Low - Priority thread, it runs in the background and stops when all user threads 
//			are terminated.
//		- It's sole purpose is to provide services to the running user threads.
//		- Any thread can be set as a daemon thread by using setDaemon() method.

public class Threads {
	public static void main (String[] args) throws IOException {
		// Thread LifeCycle
		// New -> Runnable -> Running -> Non-Runnable -> Terminated
		// Types of Creating threads : -
		
		// Extending runnable thread classes
		class Multi extends Thread {
			public void run () {
				System.out.println("Thread is Running");
			}
		}
		// Instantiate
		Multi thread = new Multi();
		// Start the thread
		thread.start();
		
		// By implementing runnable interfaces
		class Multi2 implements Runnable {
			public void run() {
				System.out.println("Thread is Running using interface");
				
			}
		}
		// Instantiate
		Multi2 thread2 = new Multi2();
		// Create a Thread
		Thread t1 = new Thread(thread2);
		// Start
		t1.start();
		
		
		// Sleep in Threads - Sleeps a thread for a specific amount of time.
		class SleepThread extends Thread {
			public void run () {
				for (int i = 0; i < 5; i++) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e);
					}
					System.out.println(i);
				}
			}
		}
		// Instantiate 2 thread objects
		SleepThread t2 = new SleepThread();
		SleepThread t3 = new SleepThread();
		// Start at the same time.
//		t2.start();
//		t3.start();
		// Here all the numbers will be printed with a delay of 500 ms before the previous.
		
		// By calling the run() method, a different Call stack is not created, it runs sequentially.
//		t2.run();
//		t3.run();
		
		// Joining a thread - To wait for a thread a finish executing.
		SleepThread t4 = new SleepThread();
		SleepThread t5 = new SleepThread();
		t4.start();
		try {
			t4.join(1700); // Waits of arg0 amount of time to finish executing, if it doesnt moves on to the next.
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		t5.start();
		
		// setName() - sets the thread name
		// getName() - gives the name to the thread
		// currentThread() - returns the instance of the current running thread in the thread class.
		// setPriority() - Thread. MIN_PROIOROTY, MAX_PRIORITY, NORM_PRIORITY
		// jconsole - Java monitoring and management console (Open in terminal)
		
		// The ShutDown Hook : -
		// It is used to do some clean up before the JVM shuts down, the hook can be used to
		// send dome alerts or log something the JVM shuts down.
		
		// Garbage Collection : -
		// 
		// In Java garbage collection happens automatically, unreferenced objects will be 
		// deleted from the memory automatically.
		//
		// finalize() Method : -
		// This method is called always before the garbage object is collected, it can be used to 
		// perform cleanup processes.
		//
		// gc() Method : -
		// This method invokes the garbage collector to perform cleanup processing. The garbage 
		// collector is a daemon thread, and it calls the finalize() method to perform the clean up
		// before the garbage collection takes place.
		//
		// Java Runtime Class : -
		// 
		// This Runtime class is used to interact with the java runtime environment, i.e you can get
		// the current free memory, invoke the Garbage Collector etc. For example the exec() method
		// can be used to execute command line commands.
	}
}
