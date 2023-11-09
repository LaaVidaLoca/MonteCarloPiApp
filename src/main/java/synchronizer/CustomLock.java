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

    public synchronized boolean tryLock() {
        if (lock) {
            return false;
        } else {
            lock = true;
            return true;
        }
    }

}
