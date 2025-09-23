package MultiThreading;

public class SynchronizedVisibilityGuarantee_9 {
    /**
     * synchronized has two important effects:
     *
     * Mutual exclusion (atomicity)
     *      Only one thread at a time can hold the lock for a synchronized block/method.
     *
     * Visibility (memory semantics)
     *      When a thread exits a synchronized block, it flushes all changes in that block to main memory.
     *      When a thread enters a synchronized block, it invalidates its local cache and
     *      reloads fresh values from main memory.
     *
     * This guarantees that a thread acquiring the same lock will see all the changes made by the
     * thread that released it.
     *
     * Happens-Before Summary (for synchronized)
     *       Exiting a synchronized block ⇒ flushes all writes to main memory.
     *       Entering a synchronized block ⇒ invalidates cache and reloads fresh values.
     * Therefore, a lock release (unlock) happens-before a lock acquire (lock) on the same monitor.
     * synchronized provides a happens-before guarantee between threads that use the same lock —
     * ensuring visibility and ordering of shared state*/
    static class SharedData {
        private boolean running = true;

        public synchronized boolean isRunning() {
            return running;
        }

        public synchronized void stop() {
            running = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();

        Thread t1 = new Thread(() -> {
            while (data.isRunning()) { } // synchronized read
            System.out.println("Thread stopped!");
        });

        t1.start();

        Thread.sleep(1000);
        data.stop(); // synchronized write
        System.out.println("Main thread set running = false");
    }
}
