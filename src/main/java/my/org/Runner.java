package my.org;

import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.websocket.WebSocketServer;

import java.io.IOException;

public class Runner {
        public static void main(String[] args) throws IOException, InterruptedException {
        Executor executor = new TestExecutor(
                SomeSmallTest.class
        );

        WebSocketServer server = new WebSocketServer(9999, executor.getMachine());
        server.start();

        Result result = executor.execute(true);
        server.stop();

        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                System.out.println(error);
            }
        }
        System.out.println("Done: [" + result.getResults().toString(2) + "]");
    }

}
