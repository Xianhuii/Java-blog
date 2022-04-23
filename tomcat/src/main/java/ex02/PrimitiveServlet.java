package ex02;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter output = res.getWriter();
        String successMessage = "HTTP/1.1 200 SUCCESS\r\n " +
                "Content-type: text/html\r\n" +
                "x-content-type-options: text/html\r\n" +
                "\r\n";
        output.write(successMessage);
        output.println("Hello Servlet");
        output.print("!!!");
//        output.flush();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
