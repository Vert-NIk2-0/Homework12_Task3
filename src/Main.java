public class Main {
    public static void main(String[] args) {
        PingPong pr = new PingPong();
        Thread Nikita = new Thread(pr, "Ping");
        Thread Maksim = new Thread(pr, "Pong");

        Nikita.start();
        Maksim.start();

    }
}