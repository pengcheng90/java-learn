package multithread;


import java.util.Random;
import java.util.concurrent.*;

public class ThreadPool {

    public static void main(String[] args) {
        //jdk线程池， Executors创建四种线程池

        //1: newSingleThreadExecutor
//        testSinglePoolExecutor(); //测试结果：多个任务只会创建一个线程执行，单个线程顺序执行任务
        //2：newFixedThreadPool
//        testFixedThreadPool(); //可以提交很多的任务task，只会核心线程执行其他任务放入队列,很难触发拒绝策略，队列LinkedBlockingQueue最大值Integer.MAX_VALUE
        //3:newCachedThreadPool
//        testCachedThreadPool();//可以创建很多线程，很难出发拒绝策略，最多可以创建Integer.MAX_VALUE个线程
        //4：newScheduledThreadPool
        testScheduledThreadPool();  //测试结果：延迟定时执行任务，只会用核心线程执行

        //通过ThreadPoolExecutor创建，通过Executors（工具类）创建的不灵活,共7个
        testThreadPoolExecutor();
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
        int max = 1000;
        final ScheduledExecutorService scheduledExecutorService = Executors.
                newScheduledThreadPool(3);
        for (int i = 0; i <= max; i++) {
            int delay = new Random().nextInt(10000);
            scheduledExecutorService.schedule(() -> {
                System.out.println("测试ScheduledThreadPool-" + Thread.currentThread().getName());
            }, delay, TimeUnit.MILLISECONDS);
        }

        //用完关闭线程池
        scheduledExecutorService.shutdown();
    }

    public static void testThreadPoolExecutor() {
        //共7个参数，最核心的参数是核心线程数
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,  //核心线程数
                50, //最大线程数
                60, //非核心线程最大空闲时间
                TimeUnit.SECONDS, //非核心线程最大空闲时间单位
                new LinkedBlockingDeque<>(10), //阻塞队列
                new ThreadFactory() {      //线程工厂
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                },
                new ThreadPoolExecutor.AbortPolicy());
    }

}
