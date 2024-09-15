import java.util.Random;

public class PingPong implements Runnable{
    private static final Object FLAG = new Object();
    private volatile boolean flagFirtsConclusion = false;
    private volatile boolean flagLastConclusion = false;


    @Override
    public void run() {
        printPingPong();
    }

    private void printPingPong() {
        Random rand = new Random();
        while (true) {
            synchronized (FLAG) {
                if (Thread.currentThread().getName().equals("Ping")) {


                    if (rand.nextInt(10) == 0 && flagFirtsConclusion) {
                        System.out.println("Nikita didn't hit the ball... \nMaxim won!");
                        flagLastConclusion = true;
                        FLAG.notify();
                        return;
                    }
                    flagFirtsConclusion = true;

                    System.out.println("Ping");

                    blockThread();
                    if (flagLastConclusion) {
                        return;
                    }
                }

                if (Thread.currentThread().getName().equals("Pong")) {

                    rand.nextInt(10);
                    if (rand.nextInt(10) == 0 && flagFirtsConclusion) {
                        System.out.println("Maxim didn't hit the ball... \nNikita won!");
                        flagLastConclusion = true;
                        FLAG.notify();
                        return;
                    }
                    flagFirtsConclusion = true;

                    System.out.println("Pong");

                    blockThread();
                    if (flagLastConclusion) {
                        return;
                    }
                }

            }
        }
    }

    private void blockThread() {
        try {
            Thread.sleep(1000);
            FLAG.notify();
            FLAG.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
