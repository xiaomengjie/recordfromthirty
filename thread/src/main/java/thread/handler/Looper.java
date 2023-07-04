package thread.handler;

import java.util.concurrent.atomic.AtomicBoolean;

public class Looper {

    private final MessageQueue messageQueue = new MessageQueue();

    private final AtomicBoolean quite = new AtomicBoolean(false);

    public void enqueueMessage(Runnable runnable){
        messageQueue.setMessage(runnable);
    }

    public void setQuite(){
        // TODO: 2023/7/4 由于使用阻塞队列，队列为空是会wait，所以需要添加消息到队列唤醒
        messageQueue.setMessage(new Runnable() {
            @Override
            public void run() {

            }
        });
        quite.set(true);
    }

    public void loop(){
        while (!quite.get()){
            Runnable runnable = messageQueue.getMessage();
            if (runnable != null){
                runnable.run();
            }
        }
    }
}
