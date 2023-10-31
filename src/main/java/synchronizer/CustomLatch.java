package synchronizer;

public class CustomLatch {
    private volatile int count;


    public CustomLatch(int count) {
        this.count = count;
    }

    public synchronized void countDown() {
        count--;
    }

    public synchronized void await() throws InterruptedException {
        if (count > 0) {
            wait();
        } else {
            notifyAll();
        }
    }

    public synchronized boolean tryAwait() {
        if (count > 0) {
            return false;
        } else {
            notifyAll();
            return true;
        }
    }
}
