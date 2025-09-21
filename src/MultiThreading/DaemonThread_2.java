package MultiThreading;

public class DaemonThread_2 {
    /**
     * A daemon thread is a background thread that provides services to user threads (non-daemon threads).
     * JVM does not wait for daemon threads to finish when the program ends.
     * When all user threads finish, the JVM will terminate and kill any running daemon threads.
     * Examples of Daemon Threads in Java
     * Garbage Collector (GC)
     * Finalizer thread
     * JVM internal housekeeping threads
     * These run in the background, but they aren’t required for the program’s completion.
     * as soon as the main thread ends, JVM shuts down and kills the daemon thread
     *
     * Key Characteristics
     *
         * Lifecycle: Daemon threads die automatically when no user threads remain.
         * Must be set before start:
         * thread.setDaemon(true);
         * thread.start();
         *
         * ❌ Throws IllegalThreadStateException if you call setDaemon(true) after start().
         *
         * Service nature: Usually used for background services like monitoring, cleanup, logging.
         * Not for important tasks: Never rely on daemon threads for tasks like writing files or sending data,
         * since they can be killed abruptly.
     *
     * Rule of thumb: Use daemon threads for background services only, not for critical tasks.*/

    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemon.setDaemon(true); // mark as daemon
        daemon.start();

        // Main (user thread)
        System.out.println("Main thread running...");
        try {
            Thread.sleep(2000); // let daemon run for a while
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished. JVM will exit now, daemon killed.");
    }
}
