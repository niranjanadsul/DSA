package MultiThreading;

public class InheriableThreadLocal_16 {
    /**
     * 🔹 ThreadLocal vs InheritableThreadLocal
     *
     *      ThreadLocal → each thread gets its own isolated copy of the variable.
     *                      Child threads do not inherit anything from the parent.
     *
     *       InheritableThreadLocal → child threads created from a parent inherit the parent’s value
     *                                  at the time of their creation.
     *                                  After that, the child has its own copy
     *                                  (modifying it won’t affect the parent).
     *
     * ✅ Key Takeaways
     *      ThreadLocal → isolated, no inheritance.
     *      InheritableThreadLocal → child thread copies the parent’s value at thread creation.
     *      After creation, parent and child values are independent
     *      (changes in one don’t affect the other).*/

    // Normal ThreadLocal
    private static ThreadLocal<String> normalThreadLocal =new ThreadLocal<>();

    // InheritableThreadLocal
    private static InheritableThreadLocal<String> inheritableThreadLocal =
            new InheritableThreadLocal<>() ;

    public static void main(String[] args) throws InterruptedException {

        Thread parent = new Thread(() -> {
            normalThreadLocal.set("Parent normal");
            System.out.println(Thread.currentThread().getName() +
                    " Normal: " + normalThreadLocal.get());
            inheritableThreadLocal.set("parent inherited");
            System.out.println(Thread.currentThread().getName() +
                    " Inheritable: " + inheritableThreadLocal.get());

            // Child thread created inside parent
            Thread child = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() +
                        " Normal: " + normalThreadLocal.get()); // won't inherit
                System.out.println(Thread.currentThread().getName() +
                        " Inheritable: " + inheritableThreadLocal.get()); // will inherit
                inheritableThreadLocal.set("Modified by Child thread");
                System.out.println(Thread.currentThread().getName() +
                        " Inheritable: " + inheritableThreadLocal.get());

            }, "Child-Thread");

            child.start();
            try {
                child.join();
            }catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() +
                    " Inheritable: " + inheritableThreadLocal.get());//not modified
        }, "Parent-Thread");

        parent.start();
        parent.join();
    }
}
