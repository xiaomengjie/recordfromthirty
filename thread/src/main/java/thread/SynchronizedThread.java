package thread;

public class SynchronizedThread extends Thread{

    private int num = 0;

    //TODO synchronized：对资源添加锁，同一时间只允许一个线程访问该资源
    //TODO 底层通过添加Monitor监控器，用来监控资源的访问
    private synchronized void numAdd(){
        num++;
    }

    @Override
    public void run() {
        new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                numAdd();
            }
            System.out.println(num);
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                numAdd();
            }
            System.out.println(num);
        }).start();
    }
}   
