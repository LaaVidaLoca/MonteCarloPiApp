import java.util.Random;

public class MonteCarloPiFinder {

    private final int pointsCount;

    public MonteCarloPiFinder(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    public double getPi() {
        Random random = new Random();
        int message = pointsCount / 10;
        double counter = 0;
        for (int i = 0; i < pointsCount; i++) {
            if ((i+1) % message == 0) {
                System.out.println("Прогресс: " + ((float) (i+1)/pointsCount*100 + "%"));
            }
            if (Math.pow(random.nextDouble(),2)
                    + Math.pow(random.nextDouble(),2) <= 1) counter++;
        }
        return counter / pointsCount * 4;
    }
}
