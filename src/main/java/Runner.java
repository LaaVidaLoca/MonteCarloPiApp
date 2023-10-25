import Holder.EagerHolder;
import Holder.LazyHolder;

public class Runner {

    private static final int TASK_COUNT = 5;
    private static final int POINTS_COUNT = 10000000;
    public static void main(String[] args) {
        ThreadController controller = new ThreadController(
                TASK_COUNT, POINTS_COUNT,"Pi task");
        controller.startTasks();
        try {
            System.out.println("Результат: " + controller.getResult());
        } catch (InterruptedException e) {
            System.out.println("Основной поток прерван");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
