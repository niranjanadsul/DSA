package MultiThreading;

//https://www.youtube.com/playlist?list=PLL8woMHwr36EDxjUoCzboZjedsnhLP1j4          Youtube playlist
//https://lnkd.in/gyJ7aihx          LeetCode problems on concurrency

import java.util.concurrent.*;

/**
 * Thread / Runnable → basic manual threads.
 * Callable / Future → threads with return values
 * ExecutorService → thread pools.
 * ScheduledExecutorService → timed tasks.
 * CompletableFuture → modern async & functional style.*/
public class ThreadCreation_1 {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread running: " + Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable running: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread t1 = new MyThread();
        t1.start(); // start() creates a new thread, run() is executed in that thread

        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        //using lambda expression
        Thread t3 = new Thread(() -> {
            System.out.println("Lambda thread: " + Thread.currentThread().getName());
        });
        t3.start();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> task = () -> {
            return "Result from " + Thread.currentThread().getName();
        };
        Future<String> future = executor.submit(task);
        System.out.println(future.get()); // waits for result
        executor.shutdown();

        executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> System.out.println("Task 1 running"));
        executor.execute(() -> System.out.println("Task 2 running"));
        executor.shutdown();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(
                () -> System.out.println("Repeating task at " + System.currentTimeMillis()),
                1, // initial delay
                2, // repeat every 2 seconds
                TimeUnit.SECONDS
        );
        // Stop scheduler after 5 seconds
        scheduler.schedule(() -> {
            System.out.println("Shutting down...");
            scheduler.shutdown();
        }, 5, TimeUnit.SECONDS);

        CompletableFuture.runAsync(() -> {
            System.out.println("Async task running in " + Thread.currentThread().getName());
        }).join();
    }
}
