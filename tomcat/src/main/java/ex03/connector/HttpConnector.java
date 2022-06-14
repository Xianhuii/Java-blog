package ex03.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 连接器
 * @author Xianhuii
 * @date 2022/4/23 22:23
 */
public class HttpConnector implements Runnable {
    private boolean stopped;
    private String scheme = "http";

    public String getScheme() {
        return scheme;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            // 1 监听端口
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped) {
            Socket socket = null;
            try {
                // 2 获取连接
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }
            // 3 处理连接
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
