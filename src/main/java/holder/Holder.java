package holder;

import java.util.concurrent.locks.ReentrantLock;

public class Holder {
    ReentrantLock lock = new ReentrantLock();
    private  int body = 0;
    private int progress = 0;
    public  void increment() {
        try {
            lock.lock();
            body++;
        } finally {
            lock.unlock();
        }
    }
    public  int incrementAndGetProgress () {
        try {
            lock.lock();
            return ++progress;
        } finally {
            lock.unlock();
        }
    }

    public int getResult() {
        try {
            lock.lock();
            return body;
        } finally {
            lock.unlock();

        }
    }
}
