package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author weixiaobin
 * @Date 2019/1/23 13:49
 */
public class NonBlockingTest {
    @Test
    public void client() throws IOException{
        //获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9998));

        //将通道切换成非阻塞模式
        socketChannel.configureBlocking(false);

        //分配一个指定大小的缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        ///4. 发送数据给服务端
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            String str = scan.next();
            byteBuffer.put((new Date().toString() + "\n" + str).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //关闭通道
        socketChannel.close();

    }

    @Test
    public void server() throws IOException{
        //获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //将通道设置成非阻塞式
        ssChannel.configureBlocking(false);

        //绑定链接,绑定端口号
        ssChannel.bind(new InetSocketAddress(9998));

        //获取选择器
        Selector selector = Selector.open();

        //将通道注册到选择器上,并且指定为“监听接受事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //轮询式的获取选择器上"已经准备就绪"的事件
        while (selector.select() > 0) {

            //获取当前选择器中所有注册的"选择键"
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                //获取准备就绪的事件
                SelectionKey selectionKey = it.next();
                //判断是什么事件
                if(selectionKey.isAcceptable()) {
                    //若"接受就绪",获取客户端链接
                    SocketChannel sChannel = ssChannel.accept();
                    //切换为非阻塞模式
                    sChannel.configureBlocking(false);
                    //将该通道注册到选择器上
                    sChannel.register(selector,SelectionKey.OP_READ);
                } else if(selectionKey.isReadable()) {
                    //获取选择器上的的"读就绪"状态的通道
                    SocketChannel schannel = (SocketChannel) selectionKey.channel();
                    //读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    int len = 0;

                    while((len = schannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }
                }
                it.remove();
            }
        }
    }
}
