import Holder.Holder;

import java.util.Random;

public class MonteCarloPiFinder {

    private final int pointsCount;

    public MonteCarloPiFinder(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    public void addInternalPointsCount(Holder holder) throws InterruptedException {
        Random random = new Random();
        int message = pointsCount / 10;
        for (int i = 0; i < pointsCount; i++) {
            if (Thread.interrupted()) throw new InterruptedException(Thread.currentThread().getName());
            if (Math.pow(random.nextDouble(),2)
                    + Math.pow(random.nextDouble(),2) <= 1) holder.increment();
        }
    }
}
