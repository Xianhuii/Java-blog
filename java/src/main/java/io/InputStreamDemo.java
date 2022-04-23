package io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

public class InputStreamDemo {
    private static String FILE_PATH = System.getProperty("user.dir") + "/target/classes/docs/io/";
    private static File TEXT_FILE = new File(FILE_PATH + "text.txt");

    /**
     * read()
     *  1、一次读取一个字节
     *  2、读取到数据返回该字节，没有读取到数据（读完了）返回-1
     */
    @Test
    public void demo01() throws IOException {
        InputStream inputStream = new FileInputStream(TEXT_FILE);
        StringBuilder sb = new StringBuilder();
        int read = inputStream.read();
        while (read != -1) {
            sb.append((char)read);
            read = inputStream.read();
        }
        inputStream.close();
        System.out.println(sb.toString());
    }

    /**
     * read(byte b[])
     *  1、从头开始，一次读取b.length个字节
     *  2、从数组的0位置开始，依次将读取到的字节添加到数组中
     *  3、读取到数据返回读取到的字节个数，没有读取到数据（读完了）返回-1
     */
    @Test
    public void demo02() throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = new FileInputStream(TEXT_FILE);
        byte[] bytes= new byte[inputStream.available()];
        int read = inputStream.read(bytes);
        inputStream.close();
        System.out.println(Arrays.toString(bytes));
    }

    /**
     *  read(byte b[], int off, int len)
     *  1、从头开始，一次读取len个字节
     *  2、从数组的off位置开始，依次将读取到的字节添加到数组中
     *  3、读取到数据返回读取到的字节个数，没有读取到数据（读完了）返回-1
     */
    @Test
    public void demo03() throws IOException {
        InputStream inputStream = new FileInputStream(TEXT_FILE);
        byte[] bytes= new byte[3];
        int read = inputStream.read(bytes, 1, 1);
        inputStream.close();
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * skip(long n)
     *  1、跳过n个字节不读
     *  2、返回跳过的字节数（与形参可能不等，因为可能在末尾没有那么多字节跳过）
     */
    @Test
    public void demo04() throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = new FileInputStream(TEXT_FILE);
        long skip = inputStream.skip(5);
        int read = inputStream.read();
        while (read != -1) {
            sb.append((char)read);
            read = inputStream.read();
        }
        inputStream.close();
        System.out.println(sb.toString());
    }

    /**
     * available()
     *  1、还剩多少字节可以读取
     */
    @Test
    public void demo05() throws IOException {
        InputStream inputStream = new FileInputStream(TEXT_FILE);
        long skip = inputStream.skip(5);
        int available = inputStream.available();
        inputStream.close();
        System.out.println(available);
    }

    /**
     * close()
     *  1、关闭流，释放资源
     */
    @Test
    public void demo07() throws IOException {
        InputStream inputStream = new FileInputStream(TEXT_FILE);
        long skip = inputStream.skip(5);
        inputStream.close();
    }

    /**
     * mark(int mark(int readlimit))
     *  1、标记当前位置
     *  2、设置标记有效的字节个数
     *  3、需要子类实现该功能
     * reset()
     *  1、重置到最近一次mark()的位置
     * markSupported()
     *  1、是否支持mark()和reset()方法
     */
    @Test
    public void demo08() throws IOException {
        InputStream inputStream = new FileInputStream(TEXT_FILE);
        long skip = inputStream.skip(5);
        inputStream.mark(10);
        int read = inputStream.read();
        while (read != -1) {
            read = inputStream.read();
        }
        if (inputStream.markSupported()) {
            inputStream.reset();
            StringBuilder sb = new StringBuilder();
            read = inputStream.read();
            while (read != -1) {
                sb.append((char)read);
                read = inputStream.read();
            }
            System.out.println(sb.toString());
        } else {
            System.out.println("FileInputStream不支持mark()和reset()");
        }
        inputStream.close();
    }
}
