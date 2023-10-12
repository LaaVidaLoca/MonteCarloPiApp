import Holder.EagerHolder;
import Holder.LazyHolder;

public class Runner {

    private static final int TASK_COUNT = 5;
    private static final int POINTS_COUNT = 10000000;
    public static void main(String[] args) {
        ThreadController controller1 = new ThreadController(
                TASK_COUNT, POINTS_COUNT, LazyHolder.getInstance(),"Task with lazy");
        controller1.startTasks();
        ThreadController controller2 = new ThreadController(
                TASK_COUNT, POINTS_COUNT, EagerHolder.INSTANCE, "Task no lazy");
        controller2.startTasks();
        System.out.println("Вычисление с ленивой инцициализацией: " + controller1.getResult());
        System.out.println("Вычисление без ленивой инцициализации: " + controller2.getResult());
    }
}
