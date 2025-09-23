package MultiThreading;

public class ThreadVisibilityUsingVolatile_8 {
    /**
     * Declaring a variable volatile tells the JVM:
     *      Always read/write directly from main memory (not cached per thread).
     *      Visibility guarantee: any update by one thread is immediately visible to others.
     * It does not make compound operations (like count++) atomic.
     *
     * When to Use volatile
     *      Good for simple flags (like isRunning, shutdown, ready).
     *      Ensures visibility, but not atomicity.
     *      For compound operations (like count++, check-then-act), use synchronized or AtomicInteger.
     *
     * volatile: guarantees visibility, prevents reordering, but not atomicity.
     * Use volatile for simple state flags, but use locks/atomics for counters and complex shared state
     *
     * happens-before relationship
     *
     *      When a thread performs a write to a volatile variable:
     *           All previous writes (to any variables) by that thread are flushed
     *           to main memory before the volatile write.
     *
 *          When a thread performs a read of a volatile variable:
     *           All subsequent reads (of any variables) by that thread will see the latest values
     *           from main memory after the volatile read.
     *
     *      This is called a happens-before relationship
     *
     * Important Notes
     * Yes: a single volatile acts as a memory barrier, flushing other variables too.
     * But: the guarantee is only about ordering relative to that volatile variable —
     *      it does not magically make non-volatile variables themselves thread-safe.*/

    static class SharedData {
        int a = 0;
        int b = 0;
        volatile boolean flag = false;
    }

    public static void main(String[] args) {
        SharedData data = new SharedData();

        Thread writer = new Thread(() -> {
            data.a = 1;           // normal write
            data.b = 2;           // normal write
            data.flag = true;     // volatile write -> flushes a and b too
        });

        Thread reader = new Thread(() -> {
            if (data.flag) {      // volatile read -> forces fresh read of a and b
                System.out.println(data.a + ", " + data.b);
            }
        });

        writer.start();
        reader.start();
    }
}
