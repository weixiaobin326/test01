package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author weixiaobin
 * @Date 2019/1/21 20:37
 */
public class NIOTest extends IOException{
    @Test
    public void test03() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get(" C:\\Users\\lenovo\\Desktop\\2019.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("C:\\Users\\lenovo\\Desktop\\2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);

            inChannel.transferTo(0,inChannel.size(),outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void test02() throws IOException {
        FileInputStream inputStream = new FileInputStream("src/3.jpg");
        FileOutputStream outputStream = new FileOutputStream("4.jpg");

        //①获取通道
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        //②创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //③将通道中的数据放入缓冲区
        while(inputChannel.read(buffer) != -1) {
            //④将缓冲区中的数据放入通道
            buffer.flip();
            outputChannel.write(buffer);
            buffer.clear();
        }

        outputChannel.close();
        inputChannel.close();
        inputStream.close();
        outputChannel.close();


    }


    @Test
    public void test01() {
        String str = "abcdef";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);

        for (byte b : bytes
             ) {
            System.out.print((char)b);
        }
        byteBuffer.flip();

        String str1 = "sdfg";

        byteBuffer.put(str1.getBytes());


        System.out.println();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }
}
