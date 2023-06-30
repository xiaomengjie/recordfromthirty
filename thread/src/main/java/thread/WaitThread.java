package thread;

public class WaitThread extends Thread{

    private String name;

    private final Object monitor = new Object();

    public void setCustomName(String name){
        synchronized (monitor){
            this.name = name;
            monitor.notify();
        }
    }

    // TODO: 2023/6/30 wait/notify必须在同步代码块内
    //TODO synchronized对象，xxx.wait，xxx.notify，必须是同一个
    //TODO synchronized (monitor), monitor.wait(), monitor.notify();
    @Override
    public void run() {
        new Thread(() -> {
            synchronized (monitor){
                while (name == null){
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(name);
            }
        }).start();
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setCustomName("Mark");
    }
}
