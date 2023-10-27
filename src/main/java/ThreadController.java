import java.util.concurrent.*;

import holder.LazyHolder;

public class ThreadController {

    private static final int SEMAPHORE_PERMITS = 3;
    private final int tasksCount;
    private final int pointsCount;
    private final ExecutorService executorService;

    private final String name;

    public ThreadController(int tasksCount, int pointsCount, String name) {
        if (pointsCount <= 0 || tasksCount <= 0) throw new IllegalArgumentException();
        this.tasksCount = tasksCount;
        this.pointsCount = pointsCount;
        this.name = name;
        executorService = Executors.newCachedThreadPool();
    }

    public void startTasks() {
        Semaphore semaphore = new Semaphore(SEMAPHORE_PERMITS);
        CountDownLatch latch = new CountDownLatch(tasksCount);
        try {
            for (int i = 0; i < tasksCount; i++) {
                executorService.execute(() -> {
                    try {
                        try {
                            semaphore.acquire();
                            TaskCountObserver.inc();
                            MonteCarloPiFinder finder = new MonteCarloPiFinder(pointsCount / tasksCount, name);
                            finder.addInternalPointsCount(pointsCount);
                        } finally {
                            TaskCountObserver.dec();
                            semaphore.release();
                        }
                        latch.countDown();
                        double start = System.currentTimeMillis();
                        latch.await();
                        double stop = System.currentTimeMillis();
                        System.out.println(Thread.currentThread().getName()
                                + " опередил общее вычисленин на " + (stop - start));
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " прерван");
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
