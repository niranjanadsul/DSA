package MultiThreading;

public class ThreadsAndMemory_3 {
    /**
     * All threads in a Java process share the same heap memory.
     * That means multiple threads can access and modify the same object instance (on the heap).
     *
     *
     * counter object lives in heap memory.
     * Both t1 and t2 have references to the same counter.
     * They update the same variable count.
     * Since no synchronization is used, the result may be less than 2000 due to race conditions.
     *
     * Objects live in heap (shared).
     * Local variables inside run() live in thread stack (isolated per thread).*/

    static class SharedCounter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter(); // stored in heap

        // Thread 1 increments counter 1000 times
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                counter.increment();
            }
        });

        // Thread 2 increments counter 1000 times
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count = " + counter.getCount());
        //count will not each time be 2 million all because of race condition
    }
}
