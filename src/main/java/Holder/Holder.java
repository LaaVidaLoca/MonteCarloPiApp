package Holder;

public class Holder {

    private volatile Integer body = 0;
    private volatile Integer progress = 0;
    public synchronized void increment() {
        body++;
    }
    public synchronized Integer incrementAndGetProgress () {
        return progress++;
    }

    public synchronized Integer getResult() {
        return body;
    }

}
