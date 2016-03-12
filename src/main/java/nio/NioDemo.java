package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by czn on 2016/3/12.
 */
public class NioDemo {
    public static Selector sel = null;
    public static Map<SocketChannel, String> sc2Path = new HashMap<SocketChannel, String>();

    public static void setConnect(String ip, String path, int port) {
        try {
            SocketChannel client = SocketChannel.open();
            client.configureBlocking(false);
            client.connect(new InetSocketAddress(ip, port));
            client.register(sel, SelectionKey.OP_CONNECT);
            sc2Path.put(client, path);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    //下载网页
    public static String readMessage(SocketChannel client) {
        String result = null;
        ByteBuffer buf = ByteBuffer.allocate(1024);
        try {
            int i = client.read(buf);
            buf.flip();
            if (i != -1) {
                result = new String(buf.array(), 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //发送HTTP请求
    public static boolean sendMessage(SocketChannel client, String msg) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf = ByteBuffer.wrap(msg.getBytes());
            client.write(buf);
            System.out.println(msg);
        } catch (IOException e) {
            return true;
        }
        return false;
    }

    public static void processSelectionKey(SelectionKey selKey) throws IOException {
        SocketChannel sChannel = (SocketChannel) selKey.channel();
        if (selKey.isValid() && selKey.isConnectable()) {
            boolean success = sChannel.finishConnect();
            if (!success) {
                selKey.cancel();
            }
            sendMessage(sChannel, "GET " + sc2Path.get(sChannel) + " HTTP/1.0\r\nAccept: */\r\n\r\n");
        } else if (selKey.isReadable()) {
            String ret = readMessage(sChannel);
            if (ret != null && ret.length() > 0) {
                System.out.println(ret);
            } else {
                selKey.cancel();
            }
        }
    }

    public static void main(String[] args) {
        try {
            sel = Selector.open();
            setConnect("www.lietu.com", "/train/search.jsp", 80);
            setConnect("book.hao123.com", "/store/c0/w0/s0/p1/all.html", 80);
            while (!sel.keys().isEmpty()) {
                if (sel.select(100) > 0) {
                    Iterator<SelectionKey> it = sel.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        try {
                            processSelectionKey(key);
                        } catch (IOException e) {
                            key.cancel();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
