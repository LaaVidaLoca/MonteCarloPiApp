package holder;

public class Holder {

    private volatile int body = 0;
    private volatile int progress = 0;
    public synchronized void increment() {
        body++;
    }
    public synchronized int incrementAndGetProgress () {
        return ++progress;
    }

    public synchronized int getResult() {
        return body;
    }

}
