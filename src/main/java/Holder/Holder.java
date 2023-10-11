package Holder;

public class Holder {

    private volatile Integer body = 0;
    public synchronized void increment() {
        body++;
    }
    public synchronized int getResult() {
        return body;
    }

}
