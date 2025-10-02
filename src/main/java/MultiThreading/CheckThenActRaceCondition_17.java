package MultiThreading;

public class CheckThenActRaceCondition_17 {
    /**
     * 🔹 What is a "Check-Then-Act" Race Condition?
     *      It happens when a thread:
     *      Checks some condition.
     *      Acts based on that condition.
     *      But between the check and the act, another thread changes the state, making the decision invalid.
     *      This happens because the check and the act are not atomic.*/
    /**
     * private boolean initialized = false;

        public void initialize() {
            if (!initialized) {  // check
                // Imagine some heavy setup here
                initialized = true;  // act
                System.out.println(Thread.currentThread().getName() + " initialized the resource");
            } else {
                System.out.println(Thread.currentThread().getName() + " found already initialized");
            }
    }

     🔎 Problem:
     Two threads call initialize() at the same time.
     Both see initialized == false.
     Both proceed to "initialize" → double initialization.
     This violates the intended design.

     🔹 How to Fix It?
     Use Atomic Classes
         import java.util.concurrent.atomic.AtomicBoolean;

         class SafeResource {
            private AtomicBoolean initialized = new AtomicBoolean(false);

             public void initialize() {
                 if (initialized.compareAndSet(false, true)) {
                    System.out.println(Thread.currentThread().getName() + " initialized the resource");
                 } else {
                    System.out.println(Thread.currentThread().getName() + " found already initialized");
                 }
             }
         }

         compareAndSet makes the check-and-act atomic in hardware (CAS operation).

     Synchronize the method/block

        public synchronized void initialize() {
            if (!initialized) {
                initialized = true;
                System.out.println(Thread.currentThread().getName() + " initialized the resource");
            } else {
                System.out.println(Thread.currentThread().getName() + " found already initialized");
            }
        }


         Now only one thread at a time can enter.
         The check+act becomes atomic.
     */

}
