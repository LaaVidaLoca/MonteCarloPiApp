package holder;

import java.util.concurrent.locks.ReentrantLock;
import synchronizer.CustomLock;

public class LazyHolder extends Holder{

    private volatile static LazyHolder instance;
    private static final CustomLock lock = new CustomLock();

    private LazyHolder() {}

    private static void init() {
        try {
            lock.lock();
            if (instance == null) {
                instance = new LazyHolder();
                System.out.println("Ленивое хранилище инициализировано");
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            lock.unlock();
        }
    }

    public static LazyHolder getInstance() {
        if (instance == null) {
                init();
            }
        return instance;
    }
}
