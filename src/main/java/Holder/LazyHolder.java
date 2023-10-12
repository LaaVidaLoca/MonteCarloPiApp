package Holder;

public class LazyHolder extends Holder{

    private  volatile LazyHolder instance;

    public LazyHolder getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new LazyHolder();
                }
            }
        }
        return instance;
    }
}
