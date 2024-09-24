package AsyncProgramming;

import java.util.concurrent.CompletableFuture;

public class AsyncProgrammingWithCompletableFuture {

    public static void main(String[] args) {

        CompletableFutureExample completableFuture = new CompletableFutureExample();

        try {
            completableFuture.run();

            System.out.println("Main thread");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
