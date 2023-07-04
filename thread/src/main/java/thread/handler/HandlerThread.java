package thread.handler;

public class HandlerThread extends Thread{

    private Handler handler;

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        if (handler != null) handler.getLooper().loop();
    }
}
