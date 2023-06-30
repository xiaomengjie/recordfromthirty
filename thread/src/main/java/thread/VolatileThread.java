package thread;

public class VolatileThread extends Thread{

    //TODO volatile保证变量的同步性
    //TODO 当变量值改变时，使用该变量的线程能及时更新获取到新值
    private volatile boolean isQuit = false;

    private void setQuit(){
        isQuit = true;
    }

    @Override
    public void run() {
        new Thread(() -> {
            while (!isQuit){

            }
            System.out.println("thread1 finished");
        }).start();

        new Thread(() -> {
            try {
                sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setQuit();
            System.out.println("thread2 finished");
        }).start();
    }
}
