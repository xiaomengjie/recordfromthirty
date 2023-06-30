package thread;

import java.util.concurrent.*;

public class CreateThread {

    // TODO: 2023/6/14 继承Thread
    public void thread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }

    // TODO: 2023/6/14 Runnable
    public void runnable(){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    // TODO: 2023/6/14 通过ThreadFactory统一创建thread
    public void threadFactory(){
        ThreadFactory threadFactory = new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        threadFactory.newThread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    // TODO: 2023/6/14 通过创建线程池统一调度线程
    public void threadPool(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    // TODO: 2023/6/14 Callback 配合 Future 可以获取返回值
    public void callback(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3_000);
                return "string";
            }
        };
        Future<String> future = Executors.newSingleThreadExecutor().submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
