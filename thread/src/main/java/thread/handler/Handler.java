package thread.handler;

public class Handler {

    private final Looper looper = new Looper();

    public Looper getLooper(){
        return looper;
    }

    public void setQuite(){
        looper.setQuite();
    }

    public void post(Runnable runnable){
        looper.enqueueMessage(runnable);
    }
}
