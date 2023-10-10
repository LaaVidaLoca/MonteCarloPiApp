public class Runner {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            MonteCarloPiFinder monteCarloPiFinder = new MonteCarloPiFinder(500000000);
            long start = System.currentTimeMillis();
            double pi = monteCarloPiFinder.getPi();
            long stop = System.currentTimeMillis();
            System.out.println("Значение Pi: " + pi + ", время: " + (stop - start) + " мс.");
        });
        thread.start();
    }
}
