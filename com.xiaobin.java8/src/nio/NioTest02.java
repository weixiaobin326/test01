package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author weixiaobin
 * @Date 2019/1/22 16:06
 */
public class NioTest02 {
    @Test
    public void client() throws IOException{
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9997));

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (inChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            sChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        sChannel.shutdownOutput();

        int len = 0;

        while((len = sChannel.read(byteBuffer)) != -1 ) {
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),0,len));
            byteBuffer.clear();
        }

        sChannel.close();
        inChannel.close();



    }

    @Test
    public void server() throws IOException{
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);

        socketChannel.bind(new InetSocketAddress(9997));

        SocketChannel sChannel = socketChannel.accept();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while(sChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        byteBuffer.put("已收到".getBytes());
        byteBuffer.flip();
        sChannel.write(byteBuffer);


        outChannel.close();
        sChannel.close();
        socketChannel.close();

    }
}
