package ex01;

import java.io.IOException;
import java.io.InputStream;

/**
 * 封装Http请求
 * @author Xianhuii
 * @date 2022/4/23 13:35
 */
public class Request {
    /**
     * Http输入流
     */
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public String getUri() {
        return uri;
    }

    /**
     * 解析http输入流
     */
    public void parse() {
        StringBuilder request = new StringBuilder(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    /**
     * 解析请求地址
     * @param requestString
     * @return
     */
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(" ");
        if (index1 != -1) {
            index2 = requestString.indexOf(" ", index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return null;
    }
}
