package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantThread extends Thread{

    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final Condition condition = reentrantLock.newCondition();

    private String string;

    public void setString(String string){
        try {
            reentrantLock.lock();
            this.string = string;
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    // TODO: wait() / notify() 都需要在同步方法，同步代码块内
    // TODO: 访问某个资源，诶，还没好，就需要在旁边等待，好了再访问
    // TODO: 而资源能不能访问都是Monitor在管理，所以等待和唤醒都需要挂在Monitor下面
    // TODO: 而Monitor只有加锁才会有

    @Override
    public void run() {
        new Thread(() -> {
            reentrantLock.lock();
            try {
                while (string == null) {
                    condition.await();
                }
                System.out.println(string + " finished");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                reentrantLock.unlock();
            }
        }).start();

        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setString("Jack");
    }
}
