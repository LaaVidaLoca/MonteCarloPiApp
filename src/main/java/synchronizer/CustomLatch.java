package synchronizer;

public class CustomLatch {
    private volatile int count;


    public CustomLatch(int count) {
        this.count = count;
    }

    public synchronized void countDown() {
        count--;
        if (count == 0) {
            notifyAll();
        }
    }

    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            wait();
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
