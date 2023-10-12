import Holder.EagerHolder;
import Holder.LazyHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonteCarloTest {

    public static final double DELTA = 0.01;
    private static final int TASK_COUNT = 5;
    private static final int POINTS_COUNT = 10000000;

   @Test
    public void test() {
       ThreadController controller1 = new ThreadController(
               TASK_COUNT, POINTS_COUNT,LazyHolder.getInstance(),"task1");
       controller1.startTasks();
       ThreadController controller2 = new ThreadController(
               TASK_COUNT, POINTS_COUNT, EagerHolder.INSTANCE,"task2");
       controller2.startTasks();
       Assertions.assertEquals(controller1.getResult(),controller2.getResult(), DELTA);
    }
}
