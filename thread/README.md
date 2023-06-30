#### 线程的使用（[CreateThread.java](src%2Fmain%2Fjava%2Fthread%2FCreateThread.java)）
    1、继承Thread，重写run方法: 
        new CreateThread.thread()

    2、创建Thread时传入Runnable: 
        new CreateThread.runnable()

    3、通过ThreadFactory统一创建线程: 
        new CreateThread.threadFactory()

    4、线程池ThreadPool: 
        new CreateThread.threadPool()

    5、Callback与FutureTask，可以获取执行返回值: 
        new CreateThread.callback()

#### 线程安全（[VolatileThread.java](src%2Fmain%2Fjava%2Fthread%2FVolatileThread.java)，[SynchronizedThread.java](src%2Fmain%2Fjava%2Fthread%2FSynchronizedThread.java)，[ReentrantThread.java](src%2Fmain%2Fjava%2Fthread%2FReentrantThread.java)）
    1、volatile：保证变量的同步性（仅在对变量的操作为原子操作时）
    2、synchronized：通过对内存添加monitor监视器，保证资源同时只会有一个线程能够访问
    3、使用ReentrantLock手动实现资源的加锁解锁

#### 线程间通信——结束线程（[StopThread.java](src%2Fmain%2Fjava%2Fthread%2FStopThread.java)、[InterruptThread.java](src%2Fmain%2Fjava%2Fthread%2FInterruptThread.java)）
    1、直接调用stop方法，线程会马上结束，可能出现不可预期的状态（不推荐）
    2、使用中断标志位通知线程结束，需要线程本身支持处理中断

#### 线程间通信——等待/唤醒（[WaitThread.java](src%2Fmain%2Fjava%2Fthread%2FWaitThread.java)）
    当线程需要的资源需要等待另一线程准备完成时，通过wait/notify方法等待唤醒