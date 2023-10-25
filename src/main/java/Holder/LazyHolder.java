package Holder;

public class LazyHolder extends Holder{

    private static volatile LazyHolder instance;

    private LazyHolder() {}

    private synchronized static void init() {
        if (instance == null) {
            instance = new LazyHolder();
            System.out.println("Ленивое хранилище инициализировано");
        }
    }

    public static LazyHolder getInstance() {
        if (instance == null) {
                init();
            }
        return instance;
    }
}
