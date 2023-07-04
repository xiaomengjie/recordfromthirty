import thread.handler.Handler;
import thread.handler.HandlerThread;

public class ThreadMain {

    public static void main(String[] args)  throws InterruptedException {
        testHandlerThread();
    }

    private static void testHandlerThread() throws InterruptedException {
        HandlerThread handlerThread = new HandlerThread();
        Handler handler = new Handler();
        handlerThread.setHandler(handler);
        handlerThread.start();

        Thread.sleep(3_000);
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable one");
            }
        });

        Thread.sleep(3_000);
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable two");
            }
        });

        Thread.sleep(1_000);
        handler.setQuite();
    }
}
