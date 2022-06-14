package ex03.connector;

import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {
    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;

    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process(Socket socket) {
        SocketInputStream  input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();
            // 1 创建HttpRequest对象
            request = new HttpRequest(input);
            // 2 创建HttpResponse对象
            response = new HttpResponse(output);
            response.setHeader("Server", "Servlet Container");
            parseRequest(input, output);
            parseHeaders(input);
            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseHeaders(SocketInputStream input) {
    }

    private void parseRequest(SocketInputStream input, OutputStream output) {
        
    }
}
