package asyncProgramming.thread;

public class AsyncProgrammingWithThreads {

    public static void main(String[] args) throws InterruptedException {

        MultiThreadExample multiThreadExample1 = new MultiThreadExample("Thread 1");
        MultiThreadExample multiThreadExample2 = new MultiThreadExample("Thread 2");
        MultiThreadExample multiThreadExample3 = new MultiThreadExample("Thread 3");

        Thread thread1 = new Thread(multiThreadExample1);
        Thread thread2 = new Thread(multiThreadExample2);
        Thread thread3 = new Thread(multiThreadExample3);

        thread1.start();
        thread2.start();
        thread3.start();

        //join bloqueia o restante do código enquanto não é finalizada a thread
        thread1.join();
        thread2.join();
        thread3.join();

        for(int i = 0; i < 200; i++){
            System.out.println("Main thread: Valor atual do loop " + i);
        }

        //thread1.join();
    }
}
