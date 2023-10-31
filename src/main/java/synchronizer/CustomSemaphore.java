package synchronizer;

public class CustomSemaphore {
    private volatile int permits;

    public CustomSemaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire(int permits) throws InterruptedException {
        if (this.permits < permits) {
            wait();
            acquire();
        } else {
            this.permits-=permits;
        }
    }

    public synchronized boolean tryAcquire(int permits) {
        if (this.permits < permits) {
            return false;
        }   else {
            this.permits-=permits;
            return true;
        }
    }

    public synchronized boolean tryAcquire() {
        return tryAcquire(1);
    }

    public synchronized void acquire() throws InterruptedException {
        acquire(1);
    }

    public synchronized void release(int permits) {
        this.permits+=permits;
        notifyAll();
    }

    public synchronized void release() {
        release(1);
    }
}
