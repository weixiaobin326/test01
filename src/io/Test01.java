package io;

import org.junit.Test;
import org.junit.runner.notification.RunListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author weixiaobin
 * @Date 2019/1/23 9:19
 */
public class Test01 {
    @Test
    public void test01() throws IOException {
        File file = new File("11.txt");
        FileInputStream inputStream = new FileInputStream(file);

        byte[] bytes = new byte[1024];

        int len;

        while ((len = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes,0,len));
        }

        inputStream.close();
    }
    @Test
    public void test02() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("11.txt"), StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int len;

        while ((len = inChannel.read(byteBuffer)) != -1) {
            System.out.println(new String(byteBuffer.array(),0,len));
        }
        inChannel.close();
    }

    @Test
    public void test03() {

        Thread a = new Thread(() -> {
            int i = 0;
            while (i < 30) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("---1---"+i);
            }

        });
        a.start();

        Thread b = new Thread(() -> {
            int l = 0;
            while (l < 30) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("---2---"+l);
            }

        });
        b.start();

    }
}
