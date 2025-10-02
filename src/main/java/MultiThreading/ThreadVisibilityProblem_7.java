package MultiThreading;

public class ThreadVisibilityProblem_7 {
    /**
     * Each thread in Java has its own working memory (cache) where it stores copies of variables from
     * main memory (heap).
     * If one thread updates a variable, another thread may not immediately see the change, because:
     *      The updated value may be sitting in the first thread’s local cache.
     *      The second thread may still be reading an old cached value.
     * This leads to a visibility problem: threads don’t “see” each other’s updates.*/
    static class SharedData {
        boolean running = true; // not volatile
    }

    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();

        Thread t1 = new Thread(() -> {
            while (data.running) { // may never see running = false
            }
            System.out.println("Thread stopped!");
        });

        t1.start();
        //t1 might run forever, because it may never see the update (false) made by the main thread.

        Thread.sleep(1000);
        data.running = false; // main thread updates
        System.out.println("Main thread set running = false");
    }
}
