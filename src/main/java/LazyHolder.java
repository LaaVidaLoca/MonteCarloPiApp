public class LazyHolder extends Holder{

    private static volatile LazyHolder instance;

    private LazyHolder() {}

    public static synchronized LazyHolder getInstance() {
        if (instance == null) {
            instance = new LazyHolder();
        }
        return instance;
    }
}
