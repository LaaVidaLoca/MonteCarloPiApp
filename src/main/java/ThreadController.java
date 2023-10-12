import Holder.Holder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadController {
    private final static int THREAD_POOL_SIZE = 5;
    private final int tasksCount;
    private final int pointsCount;
    private final Holder holder;
    private final ExecutorService executorService;

    private String name;

    public ThreadController(int tasksCount, int pointsCount, Holder holder, String name) {
        if (pointsCount <= 0 || tasksCount <= 0) throw new IllegalArgumentException();
        this.tasksCount = tasksCount;
        this.pointsCount = pointsCount;
        this.holder = holder;
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
                        finder.addInternalPointsCount(holder,pointsCount);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage() + " прерван");
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
    }

    public double getResult() {
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            System.out.println("Основной поток прерван");
        }
        return (double) holder.getResult() / pointsCount * 4;
    }
}
