package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool_19 {
    /**
     * 🔹 What is a Thread Pool?
     *      A thread pool is a collection of pre-created, reusable threads managed by the JVM.
     *      Instead of creating a new thread every time you have a task, you submit the task to the pool.
     *      The pool assigns it to an available worker thread, or queues it until a thread is free.
     *
     * ✅ Benefits:
     *      Avoids overhead of creating/destroying threads repeatedly.
     *      Controls the number of concurrent threads (prevents system overload).
     *      Can reuse threads efficiently for many short-lived tasks.
     *
     🔹 Core Concepts
         In Java, thread pools are provided by ExecutorService (from java.util.concurrent).
             Executors.newFixedThreadPool(n) → Pool with fixed number of threads.
             Executors.newCachedThreadPool() → Expands/shrinks dynamically.
             Executors.newSingleThreadExecutor() → Single worker thread.
             ScheduledExecutorService → For periodic/scheduled tasks.
     */
    // A sample task
    static class WorkerTask implements Runnable {
        private final int taskId;

        public WorkerTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Task " + taskId + " started by " + threadName);
            try {
                Thread.sleep(1000); // simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Task " + taskId + " finished by " + threadName);
        }
    }

    public static void main(String[] args) {
        // Create a thread pool with 3 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 10 tasks to the pool
        for (int i = 1; i <= 10; i++) {
            executor.submit(new WorkerTask(i));
        }
//        10 tasks submitted.
//        Only 3 run at once, rest wait.

        // Initiate shutdown (no new tasks accepted, existing tasks continue)
        executor.shutdown();

        try {
            // Wait for tasks to complete or timeout
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // force shutdown if tasks didn't finish
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks completed. Main thread exiting.");
    }

    /**
     * 🔹 Key Things to Remember

        execute(Runnable) vs submit(Runnable/Callable)
                execute() → fire-and-forget.
                submit() → returns a Future, so you can get result or handle exceptions.
        Use Callable<T> when you want results.
        Always shutdown the pool to release resources.
        For production code, prefer ThreadPoolExecutor constructor (gives full control over queue, rejection policy, etc.).*/
}
