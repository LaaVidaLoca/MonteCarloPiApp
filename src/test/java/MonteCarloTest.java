import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import synchronizer.CustomLatch;
import synchronizer.CustomLock;
import synchronizer.CustomSemaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarloTest {

    private static final int LATCH_COUNT = 10;
    private static final int SEMAPHORE_PERMITS = 10;

    private static final int TASKS = 20;
    private static final long DELTA = 10;
    private final Random random = new Random();

    private final ExecutorService executorService = Executors.newCachedThreadPool();

   @Test
    public void customLatchAwaitTest() throws InterruptedException {
       CustomLatch latch = new CustomLatch(LATCH_COUNT);
       long[] timeList = new long[LATCH_COUNT];
       try {
           for (int i = 0; i < LATCH_COUNT; i++) {
               int thisI = i;
               executorService.execute(() -> {
                   try {
                       Thread.sleep(random.nextInt(0,5) * 1000L);
                       latch.countDown();
                       latch.await();
                       timeList[thisI] = (System.currentTimeMillis());
                   } catch (InterruptedException e) {
                       throw new IllegalStateException(e);
                   }
               });
           }
       } finally {
           executorService.shutdown();
       }
       if (!executorService.awaitTermination(5, TimeUnit.MINUTES))
           throw new IllegalStateException("Превышено время ожидания");
       long currentTime = System.currentTimeMillis();
       for (long item: timeList) {
           Assertions.assertEquals(item,currentTime,DELTA);
       }
   }

   @Test
    public void latchAwaitTest() throws InterruptedException {
        CustomLatch latch = new CustomLatch(1);
       executorService.execute(() -> {
           try {
               latch.await();
               System.out.println("освобождение");
           } catch (InterruptedException e) {
               throw new IllegalStateException(e);
           }
       });
       Thread.sleep(100);
       executorService.execute(latch::countDown);
       executorService.shutdown();
       executorService.awaitTermination(5, TimeUnit.MINUTES);
    }

   @Test
    public void customLatchTestTryAwait() {
        CustomLatch latch = new CustomLatch(1);
       Assertions.assertFalse(latch.tryAwait());
    }

    @Test
    public void customLockTryLockTest() throws InterruptedException {
        CustomLock lock = new CustomLock();
        Assertions.assertTrue(lock.tryLock());
        Assertions.assertFalse(lock.tryLock());
    }

    @Test
    public void customLockLockTest() throws InterruptedException {
        CustomLock lock = new CustomLock();
        AtomicInteger active = new AtomicInteger();
        try {
            for (int i = 0; i < TASKS; i++) {
                executorService.execute(() -> {
                    try {
                        lock.lock();
                        active.getAndIncrement();
                        for (int j = 0; j < 10; j++) {
                            Thread.sleep(random.nextInt(0,5) * 10L);
                            Assertions.assertTrue(active.get() <= 1);
                        }
                        active.getAndIncrement();
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    } finally {
                        lock.unlock();
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
        if (!executorService.awaitTermination(5, TimeUnit.MINUTES))
            throw new IllegalStateException("Превышено время ожидания");
    }
    @Test
    public void customSemaphoreAcquireReleaseTest() throws InterruptedException {
        CustomSemaphore semaphore = new CustomSemaphore(SEMAPHORE_PERMITS);
        try {
            for (int i = 0; i < TASKS; i++) {
                executorService.execute(() -> {
                    try {
                        semaphore.acquire();
                        TaskCountObserver.inc();
                        for (int j = 0; j < 10; j++) {
                            Thread.sleep(random.nextInt(0,5) * 100L);
                            Assertions.assertTrue(TaskCountObserver.getActiveCount() <= SEMAPHORE_PERMITS);
                        }
                        TaskCountObserver.dec();
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    } finally {
                        semaphore.release();
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
        if (!executorService.awaitTermination(5, TimeUnit.MINUTES))
            throw new IllegalStateException("Превышено время ожидания");
    }
}
