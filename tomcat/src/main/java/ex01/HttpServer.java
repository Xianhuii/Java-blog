package ex01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Web服务器
 *
 * @author Xianhuii
 * @date 2022/4/23 13:22
 */
public class HttpServer {
    /**
     * 资源根目录
     */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "tomcat" + File.separator + "webroot";
    /**
     * 关闭命令
     */
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    /**
     * 关闭标识
     */
    private boolean shutdown = false;

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            // 监听
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                // 等待客户端请求
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();
                // 解析输入流，解析http请求
                Request request = new Request(input);
                request.parse();
                // 创建http响应对象
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();
                // 关闭连接
                socket.close();
                shutdown = SHUTDOWN_COMMAND.equals(request.getUri());
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }
}
