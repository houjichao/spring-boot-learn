package com.hjc.learn.demo.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket Server
 * 1、建立服务器端
 * 服务器建立通信ServerSocket
 * 服务器建立Socket接收客户端连接
 * 建立IO输入流读取客户端发送的数据
 * 建立IO输出流向客户端发送数据消息
 *
 * @author houjichao
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(18888);
            System.out.println("启动服务器....");
            Socket socket = serverSocket.accept();
            System.out.println("客户端：" + socket.getInetAddress().getHostAddress() + "已连接到服务器");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取客户端发来的消息
            String mess = br.readLine();
            System.out.println("客户端消息:" + mess);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(mess + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
