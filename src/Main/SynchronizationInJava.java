package Main;

import java.io.*;
import java.util.*;

// Synchronization in Java is the ability to control the access of multiple threads to any shared 
// resource. It is used to prevent thread interface and to prevent consistency problem.
// 


public class SynchronizationInJava {
	public static void main (String[] args) throws IOException {
		// Synchronised Method
		class PrintTableUnSync {
			void Print(int n) {
				for (int i = 1; i <= 5; i++) {
					System.out.println(i * n);
					try {
						Thread.sleep(400);
					} catch (Exception e) {
						System.out.println(e.getStackTrace());
					}
				}
			}
		}
		// UnSync Method
		class PrintTableSync {
			synchronized void Print(int n) {
				for (int i = 1; i <= 5; i++) {
					System.out.println(i * n);
				}
				try {
					Thread.sleep(400);
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}
			}
		}
		class SyncThread extends Thread {
			PrintTableSync t;
			int n;
			SyncThread (int n) {
				this.t = new PrintTableSync();
				this.n = n;
			}
			
			public void run () {
				this.t.Print(this.n);
			}
		}
		
		// Run the Sync Thread
		SyncThread t1 = new SyncThread(5), t2 = new SyncThread(100);
		t1.start();
		t2.start();
		
		// Synchronized Block
		synchronized (new SyncThread(5)) {
			// Everything in this block will be synchronized.
			// The referenced object is locked in this specific resource.
			;
		}
		
		// Deadlock
		// This occurs when there are two threads and they are both waiting for a thread to
		// be completed and the threads they are waiting for is them both itself. In this case
		// the process will go into an infinite loop and wont give any result. A system must
		// be designed in which this case is to be avoided.
		
		// Inter-Thread Communincation
		// There is a way Threads can communicate with each other. The wait() and sleep() methods 
		// are used in inter-thread communication.
		class Customer {
			int amount = 1000;
			
			synchronized void Withdraw (int x) {
				System.out.println("Withdrawing");
				
				if (x > amount) {
					System.out.println("Low Balance! waiting for Deposit");
					try {
						wait(700);
					} catch (Exception e) {
						System.out.println(e.getStackTrace());
					}
					this.amount -= x;
					System.out.println("New Balance is " + this.amount);
				}
			}
			
			synchronized void Deposit (int x) {
				System.out.println("Depositing");
				this.amount += x;
				System.out.println(x + " amount deposited");
				notify(); // Notifies the thread that the money is deposited.
			}
		}
		
		final Customer c = new Customer();
		new Thread () {
			public void run () {
				c.Withdraw(1500);
			}
		}.start();
		new Thread () {
			public void run () {
				c.Deposit(500);
			}
		}.start();
	}
}
