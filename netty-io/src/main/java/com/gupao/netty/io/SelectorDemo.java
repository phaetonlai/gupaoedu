package com.gupao.netty.io;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/6/22
 * @description
 **/
public class SelectorDemo {
    private static Selector selector;

    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws Exception {
        selector = openSelector(80);

        listen(selector);
    }

    private static void listen(Selector selector) {
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.keys();
                Iterator<SelectionKey> itr = keys.iterator();
                while (itr.hasNext()) {
                    SelectionKey key = itr.next();
                    itr.remove();
                    process(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Selector openSelector(int port) throws Exception {
        Selector selector = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        ServerSocket serverSocket = server.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);

        server.register(selector, SelectionKey.OP_ACCEPT);

        return selector;
    }

    private static void process(SelectionKey key) throws Exception {
        if (key.isAcceptable()) {
            //
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        }
        else if (key.isReadable()) {
            //
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
                sKey.attach(content);
            }
            else {
                channel.close();
            }

            buffer.clear();
        }
        else if (key.isWritable()) {
            //
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            ByteBuffer block = ByteBuffer.wrap((content).getBytes());
            if (block != null) {
                channel.write(block);
            }
            else {
                channel.close();
            }
        }
    }
}
