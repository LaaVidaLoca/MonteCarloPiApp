package holder;

import java.util.concurrent.locks.ReentrantLock;

public class LazyHolder extends Holder{

    private static volatile LazyHolder instance;
    static ReentrantLock lock = new ReentrantLock();

    private LazyHolder() {}

    private static void init() {
        if (instance == null) {
            try {
                lock.lock();
            } finally {
                instance = new LazyHolder();
                System.out.println("Ленивое хранилище инициализировано");
                lock.unlock();
            }
        }
    }

    public static LazyHolder getInstance() {
        if (instance == null) {
                init();
            }
        return instance;
    }
}
