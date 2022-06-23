/**
 * @author q2333
 * @date 2022/06/22 10:01
 **/

public class ThreadCount {
    //    public static Object lock = new Object();//锁
    public static int ticketNum = 10000;
    public static int threadUnits = 16;
    public static int sleepTime = 0;
    public static int mainSleepSecond = 5;
    public static int realSold;
    public static int originTicketNum = ticketNum;

    public static void main(String[] args) throws InterruptedException {
        int priority = 5;//   bwtween1-10 ,normal=5;
        for (int i = 0; i < threadUnits; i++) {
            String s = "thread" + i;
            if (priority <= 10) {
                createAndRunCounter_Thread(s, priority++);
            } else {
                priority = 10;
                createAndRunCounter_Thread(s, priority);
            }
            System.out.println(s + "created");
        }
        //如何修改上面循环中的线程的优先级?他在代码中还没有运行则没有初始化?//todo
        //考虑使用反射?运行时获取对象线程名称以及对象线程方法setPriority?
        //等待3个线程完成.考虑使用wait?>待学习
        Thread.sleep(mainSleepSecond * 1000L);//
        System.out.println("ticket total: " + originTicketNum);
        System.out.println(threadUnits + " thread realSold: " + realSold);
        if (realSold != originTicketNum) {
            System.out.println("sold wrong");
        } else {
            System.out.println("sell right");
        }
    }

    public static void createAndRunCounter_Thread
            (String name, int priority) {
        Counter counter = new Counter();
        final Thread thread = new Thread(counter);
        thread.setName(name);
        thread.setPriority(priority);
        thread.start();
    }
}

class Counter implements Runnable {

    @Override
    public void run() {//线程启动
        synchronized (ThreadCount.class) {
            sellTicket();
        }
//       sellTicket();
    }

    public void sellTicket() {
        int ThreadSold = 0;
        for (int i = 0; i < ThreadCount.originTicketNum / ThreadCount.threadUnits; i++) {
            try {
                //临界值判断条件重要.如何防止发生超卖?
                if (ThreadCount.ticketNum <= 0) {
                    System.out.println("ticket sold out");
                    break;
                } else {
                    ThreadCount.ticketNum--;
                    ThreadSold++;
//                    System.out.println(Thread.currentThread().getName() + " sold " + ThreadSold + " ticket ");
//                    System.out.println("ticket remain: " + ThreadCount.ticketNum);
                    if (ThreadCount.sleepTime!=0){
                        Thread.sleep(ThreadCount.sleepTime);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() +
                " sold total " + ThreadSold + " ticket ");
        ThreadCount.realSold += ThreadSold;
    }
}

