package ex03.startup;

import ex03.connector.HttpConnector;

/**
 * 启动类
 * @author Xianhuii
 * @date 2022/4/23 22:22
 */
public final class Bootstrap {
    public static void main(String[] args) {
        // 1 启动连接器
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
