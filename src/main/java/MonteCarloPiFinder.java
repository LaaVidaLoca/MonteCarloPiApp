import Holder.Holder;

import java.util.Random;

public class MonteCarloPiFinder {

    private final int pointsCount;
    private final String poolName;

    public MonteCarloPiFinder(int pointsCount, String poolName) {
        this.pointsCount = pointsCount;
        this.poolName = poolName;
    }

    public void addInternalPointsCount(Holder holder, int allPoints) throws InterruptedException {
        Random random = new Random();
        int message = allPoints / 10;
        int progress;
        for (int i = 0; i < pointsCount; i++) {
            if (Thread.interrupted()) throw new InterruptedException(Thread.currentThread().getName());
            progress = holder.incrementAndGetProgress();
            if (progress % message == 0) {
                System.out.println("Прогресс " + poolName + ": " + ((float) progress/allPoints*100) + "%");
            }
            if (Math.pow(random.nextDouble(),2)
                    + Math.pow(random.nextDouble(),2) <= 1) holder.increment();
        }
    }
}
