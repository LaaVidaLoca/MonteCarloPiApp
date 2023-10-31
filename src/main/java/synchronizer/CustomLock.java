package synchronizer;

public class CustomLock {
    private volatile boolean lock;

    public CustomLock() {
        lock = false;
    }

    public synchronized void lock() throws InterruptedException {
       if (lock) {
           wait();
           lock();
       } else {
           lock = true;
       }
    }

    public synchronized void unlock() {
        lock = false;
        notifyAll();
    }
}
