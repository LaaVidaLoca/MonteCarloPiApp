import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonteCarloTest {

    public static final double DELTA = 0.01;
    private static final int TASK_COUNT = 5;
    private static final int POINTS_COUNT = 10000000;

   @Test
    public void test() {
       ThreadController controller = new ThreadController(
               TASK_COUNT, POINTS_COUNT,"task");
       controller.startTasks();
       try {
           Assertions.assertEquals(controller.getResult(),Math.PI, DELTA);
       } catch (InterruptedException e) {
           System.out.println("Поток тестирования прерван");
       } catch (IllegalStateException e) {
           System.out.println(e.getMessage());
       }
   }
}
