import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class MonteCarloTest {

    public static final double DELTA_1 = 0.001;
    public static final double DELTA_2 = 0.01;


   @RepeatedTest(3)
    public void test1() {
       MonteCarloPiFinder monteCarloPiFinder = new MonteCarloPiFinder(100000000);
       Assertions.assertEquals(monteCarloPiFinder.getPi(), Math.PI, DELTA_1);
    }

    @RepeatedTest(3)
    public void test2() {
        MonteCarloPiFinder monteCarloPiFinder = new MonteCarloPiFinder(100000);
        Assertions.assertEquals(monteCarloPiFinder.getPi(), Math.PI, DELTA_2);
    }
}
