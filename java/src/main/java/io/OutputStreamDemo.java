package io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class OutputStreamDemo {
    private static String FILE_PATH = System.getProperty("user.dir") + "/target/classes/docs/io/";
    private static File FILE_OUT = new File(FILE_PATH + "out.txt");
    /**
     *  write(int b)
     *      - 写出一个字节
     */
    @Test
    public void demo01() throws IOException {
        OutputStream outputStream =  new FileOutputStream(FILE_OUT);
        outputStream.write(65);
        outputStream.close();
    }

    /**
     *  write(byte b[])
     *      - 写出b.length个字节
     */
    @Test
    public void demo02() throws IOException {
        OutputStream outputStream = new FileOutputStream(FILE_OUT);
        byte[] bytes = new byte[]{65, 66};
        outputStream.write(bytes);
        outputStream.close();
    }

    /**
     *  write(byte b[], int off, int len)
     *      - b数组中[off, off+len)位置的字节写出
     */
    @Test
    public void demo03() throws IOException {
        OutputStream outputStream = new FileOutputStream(FILE_OUT);
        byte[] bytes = new byte[]{65, 66};
        outputStream.write(bytes, 1, 1);
        outputStream.close();
    }

    /**
     *  flush()
     *      - 将缓冲输出流中的缓存清空
     *      - 对普通输出流没用
     */
    @Test
    public void demo04() throws IOException {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FILE_OUT));
        byte[] bytes = new byte[]{65, 66};
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    /**
     *  close()
     *      - 关闭输出流，释放资源
     */
    @Test
    public void demo05() throws IOException {
        OutputStream outputStream = new FileOutputStream(FILE_OUT);
        byte[] bytes = new byte[]{65, 66};
        outputStream.write(bytes);
        outputStream.close();
    }
}
