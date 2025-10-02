package MultiThreading;

public class ThreadLocal_15 {
    /**
     * What is ThreadLocal?
     *
     *      A ThreadLocal<T> provides thread-local variables.
     *      Each thread that accesses the variable via get()/set() has its own independent copy.
     *      This means values stored in a ThreadLocal are not shared between threads —
     *      they’re bound to the thread that set them.
     *
     * 🔹 Why do we need ThreadLocal?
     *      Sometimes we need shared state per thread (not across threads).
     *      Example:
     *          Database connections per thread.
     *          User/session context in web servers.
     *          DateFormat (not thread-safe) reused safely per thread.
     *
     * 🔹 Key Methods
     *      get() → fetch the value for current thread.
     *      set(T value) → set value for current thread.
     *      remove() → remove value (important to avoid memory leaks in long-lived threads like
     *                  thread pools).
     *      withInitial(Supplier<? extends T>) → convenient way to provide default values.
     *
     * 🔹 Important Notes
     *
     *      Memory leaks risk in thread pools:
     *      If you don’t call remove(), values can stick around since threads are reused.
     *      ThreadLocal is not Inheritable:
     *      By default, child threads don’t inherit values.
     *      Use InheritableThreadLocal if you need that.
     *      Use case: great for context storage (like a mini per-thread global variable).*/

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                int value = threadLocal.get();
                threadLocal.set(value + 1);
                System.out.println(Thread.currentThread().getName() + " -> " + threadLocal.get());
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
