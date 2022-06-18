/**
 * @author q2333
 * @date 2022/06/21 06:57
 **/
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        final Task01 task01 = new Task01();
        task01.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main running...");
            Thread.sleep(1000);
        }
    }
}
class Task01 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("task01 runing...");
                Task01.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

