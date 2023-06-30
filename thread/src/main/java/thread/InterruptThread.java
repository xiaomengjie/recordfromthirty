package thread;

public class InterruptThread extends Thread{

    // TODO: 2023/6/14 thread.interrupt(): 将thread线程的中断位标记微true
    // TODO: 2023/6/14 而是否结束线程，在于thread对中断信号的处理
    // TODO: 2023/6/14 如果不处理，线程会继续执行，不会结束

    // TODO: 2023/6/14 InterruptedException线程处于休眠等待状态时，此时如果要求线程中断
    // TODO: 2023/6/14 会直接抛出InterruptedException，通知线程，之后中断位会置为false
    // TODO: 2023/6/14 方法有：sleep, wait, join

    @Override
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                interrupt();
            }
        }).start();

        while (!isInterrupted()){

        }
        System.out.println("finish");
    }
}
