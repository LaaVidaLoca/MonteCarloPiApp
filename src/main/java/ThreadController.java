import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadController {
    private final static int THREAD_POOL_SIZE = 5;
    private final int tasksCount;
    private final int pointsCount;

    Holder holder = LazyHolder.getInstance();
    ExecutorService executorService;

    public ThreadController(int tasksCount, int pointsCount) {
        if (pointsCount <= 0 || tasksCount <= 0) throw new IllegalArgumentException();
        this.tasksCount = tasksCount;
        this.pointsCount = pointsCount;
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void startTasks() {
        for (int i = 0; i < tasksCount; i++) {
            executorService.execute(() -> {
                MonteCarloPiFinder finder = new MonteCarloPiFinder(pointsCount/tasksCount);

            });
        }
    }

}
