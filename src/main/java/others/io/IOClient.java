package others.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @auther: liweizhi
 * Date: 2019/3/26 17:38
 * Description:
 */
public class IOClient {
    public static void main(String[] args) {
        sendRequest();
    }

    private static void sendRequest() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8000);
            for (int i = 0; i < 3; i++) {
                try {
                    socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                    socket.getOutputStream().flush();
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }

        } catch (IOException e) {
        } finally {
            try {
                if (socket != null && !socket.isClosed())
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
