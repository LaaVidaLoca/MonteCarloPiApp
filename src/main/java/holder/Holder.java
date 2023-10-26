package holder;

import java.util.concurrent.locks.ReentrantLock;

public class Holder {
    ReentrantLock lock = new ReentrantLock();
    private volatile int body = 0;
    private volatile int progress = 0;
    public  void increment() {
        lock.lock();
        body++;
        lock.unlock();
    }
    public  int incrementAndGetProgress () {
        lock.lock();
        int result = ++progress;
        lock.unlock();
        return result;

    }

    public int getResult() {
        lock.lock();
        int result = body;
        lock.unlock();
        return result;

    }
}
