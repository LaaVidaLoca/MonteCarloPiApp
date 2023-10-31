package holder;

import synchronizer.CustomLock;

import java.util.concurrent.locks.ReentrantLock;

public class Holder {
    CustomLock lock = new CustomLock();
    private  int body = 0;
    private int progress = 0;
    public  void increment() {
        try {
            lock.lock();
            body++;
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        } finally {
            lock.unlock();
        }
    }
    public  int incrementAndGetProgress () {
        try {
            lock.lock();
            return ++progress;
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            lock.unlock();
        }
    }

    public int getResult() {
        try {
            lock.lock();
            return body;
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        finally {
            lock.unlock();
        }
    }
}
