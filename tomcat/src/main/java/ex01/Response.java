package ex01;

import java.io.*;

/**
 * Http响应对象
 * @author Xianhuii
 * @date 2022/4/23 13:38
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream output;
    private Request request;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * 发送静态资源
     */
    public void sendStaticResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                String successMessage = "HTTP/1.1 200 SUCCESS\r\n " +
                        "Content-type: text/html\r\n" +
                        "x-content-type-options: text/html\r\n" +
                        "\r\n";
                output.write(successMessage.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n " +
                        "Content-type: text/html\r\n " +
                        "x-content-type-options: text/html\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
