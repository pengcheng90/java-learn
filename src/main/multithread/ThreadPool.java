package multithread;

import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static void main(String[] args) {
        //jdk线程池

        //1: newSingleThreadExecutor
//        testSinglePoolExecutor(); //测试结果：多个任务只会创建一个线程执行，单个线程顺序执行任务
        //2：newFixedThreadPool
//        testFixedThreadPool(); //可以提交很多的任务task,很难触发拒绝策略，队列LinkedBlockingQueue最大值Integer.MAX_VALUE
        //3:newCachedThreadPool
//        testCachedThreadPool();//可以创建很多线程，很难出发拒绝策略，最多可以创建Integer.MAX_VALUE个线程
        //4：newScheduledThreadPool
        testScheduledThreadPool();  //测试结果：延迟定时执行任务，只会用核心线程执行
    }


    public static void testSinglePoolExecutor() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("测试SinglePoolExecutor-" + Thread.currentThread().getName());
            });

        }

        //用完销毁
        executorService.shutdown();
    }

    public static void testFixedThreadPool() {
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 300; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("测试FixedThreadPool-" + Thread.currentThread().getName());
            });
        }

        //用完关闭线程池
        executorService.shutdown();
    }

    public static void testCachedThreadPool() {
        System.out.println("创建" + Integer.MAX_VALUE + "个线程才会触发拒绝策略");
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i < 500; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("测试CachedThreadPool-" + Thread.currentThread().getName());
            });
        }

        //用完关闭线程池
        executorService.shutdown();
    }

    public static void testScheduledThreadPool() {
        final ScheduledExecutorFactoryBean scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
        int max = 1000;
        final ScheduledExecutorService scheduledExecutorService = Executors.
                newScheduledThreadPool(3, scheduledExecutorFactoryBean);
        for (int i = 0; i <= max; i++) {
            int delay = new Random().nextInt(10000);
            scheduledExecutorService.schedule(()-> {
                System.out.println("测试ScheduledThreadPool-" + Thread.currentThread().getName());
            }, delay, TimeUnit.MILLISECONDS);
        }
    }

}
