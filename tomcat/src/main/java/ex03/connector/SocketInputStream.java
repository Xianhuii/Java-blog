package ex03.connector;

import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {
    public SocketInputStream(InputStream inputStream, int i) {

    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
