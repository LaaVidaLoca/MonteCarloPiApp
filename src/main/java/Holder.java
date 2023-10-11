public class Holder {

    private Integer body = 0;
    public void increment() {
        synchronized (body) {
            body++;
        }
    }
    public int getResult() {
        synchronized (body) {
            return body;
        }
    }

}
