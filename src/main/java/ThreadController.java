import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import holder.LazyHolder;

public class ThreadController {
    private final static int THREAD_POOL_SIZE = 5;
    private final int tasksCount;
    private final int pointsCount;
    private final ExecutorService executorService;

    private final String name;

    public ThreadController(int tasksCount, int pointsCount, String name) {
        if (pointsCount <= 0 || tasksCount <= 0) throw new IllegalArgumentException();
        this.tasksCount = tasksCount;
        this.pointsCount = pointsCount;
        this.name = name;
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public void startTasks() {
        try {
            for (int i = 0; i < tasksCount; i++) {
                executorService.execute(() -> {
                    MonteCarloPiFinder finder = new MonteCarloPiFinder(pointsCount/tasksCount,
                            name);
                    try {
                        finder.addInternalPointsCount(pointsCount);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage() + " прерван");
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
    }

    public double getResult() throws InterruptedException, IllegalStateException {
            boolean terminationResult = executorService.awaitTermination(5, TimeUnit.MINUTES);
            if (!terminationResult) throw new IllegalStateException("Превышено время ожидания");
        return (double) LazyHolder.getInstance().getResult() / pointsCount * 4;
    }
}
