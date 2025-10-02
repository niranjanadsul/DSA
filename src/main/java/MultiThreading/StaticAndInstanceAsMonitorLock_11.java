package MultiThreading;

public class StaticAndInstanceAsMonitorLock_11 {
    /**🔹 1
     * Instance Synchronized Method
     *      The monitor object here is the current instance (this).
     *      Only one thread at a time can call instanceMethod() on the same object.
     *      But if you have two different objects, their locks are independent.
     *
     *      */
    /**
             static class Example {
                public synchronized void instanceMethod() {
                    // synchronized on "this"
                }
            }

            public static void main(String[] args) {
                Example e1 = new Example();
                Example e2 = new Example();

                Thread t1 = new Thread(e1::instanceMethod);
                Thread t2 = new Thread(e2::instanceMethod);

                t1.start();
                t2.start();

            }

     Both can run in parallel, since t1 locks e1 and t2 locks e2.
     */

    /**🔹 2
     * Synchronized Block on Instance
     *      Equivalent to synchronized method.
     *      Lock is acquired on this (the object instance).
     *      Advantage: you can limit the synchronized scope instead of locking the whole method.
     * */
    /**
            class Example {
                public void method() {
                    synchronized(this) {
                        // critical section
                    }
                }
            }
     */
    /**🔹 3. Static Synchronized Method
     *      The monitor object here is the Class object itself: Example.class.
     *      Only one thread at a time can execute a static synchronized method across all instances of the class.
     *      Even if you create multiple objects, they share the same Class lock.
     * */
    /**
        class Example {
            public static synchronized void staticMethod() {
                // synchronized on Example.class
            }
        }

        public static void main(String[] args) {
            Example e1 = new Example();
            Example e2 = new Example();

            Thread t1 = new Thread(Example::staticMethod);
            Thread t2 = new Thread(e1::instanceMethod);

            t1.start();
            t2.start();

        }

        They don’t block each other —
            because one uses Example.class (static lock) and the other uses e1 (instance lock).
     */

    /**
     * 🔹 4. Synchronized Block on Class
     *      Explicitly locks on the Class object.
     *      Equivalent to a static synchronized method,
     *      but gives more control (you can narrow the block scope).
     * */
    /**
     * class Example {
            public void method() {
                synchronized(Example.class) {
                    // critical section
                }
            }
        }
     */

    /**
     * ✅ Key Takeaway:
     *      Instance locks (non-static synchronized methods/blocks) → lock per object.
     *      Class locks (static synchronized methods/blocks) → lock per class, shared across all objects.*/


}
