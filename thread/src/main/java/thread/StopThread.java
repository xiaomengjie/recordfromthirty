package thread;

public class StopThread extends Thread{

    @Override
    public void run() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000_000; i++) {
                    System.out.println(i);
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.stop();
    }
}
