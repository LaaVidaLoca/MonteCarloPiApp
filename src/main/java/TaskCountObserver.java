public class TaskCountObserver {
    private static volatile int activeCount = 0;

    public static synchronized void inc() {
        activeCount++;
    }

    public static synchronized void dec() {
        activeCount--;
    }

    public static synchronized int getActiveCount() {
        return activeCount;
    }
}
