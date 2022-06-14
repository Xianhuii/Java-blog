package ex03.connector;

import ex02.Request;
import ex02.Response;

public class StaticResourceProcessor {
    public void process(HttpRequest request, HttpResponse response) {
        response.sendStaticResource();
    }
}
