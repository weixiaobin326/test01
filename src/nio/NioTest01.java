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
 * @Date 2019/1/22 14:21
 */
public class NioTest01 {

    @Test
    public void client() throws IOException {
        //开启一个SocketChannel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9988));

        //开启一个获取本地资源的文件通道
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        //创建一个缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //读取文件到缓存区并将数据写到socket通道，发送到服务端
        while((inChannel.read(byteBuffer) != -1)) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //将所有的通道关闭
        inChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException{
        //开启一个接收端的socket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //开启一个本地的文件通道，用于将客户端传来的数据传入本地
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        //为该通道绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9988));
        //接收客户端传来的数据
        SocketChannel socketChannel = serverSocketChannel.accept();
        //分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //接收客户端传来的数据到缓冲区，并保存到本地
        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //将所有的通道关闭
        outChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }
}
