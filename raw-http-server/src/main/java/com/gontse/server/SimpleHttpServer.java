package com.gontse.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    public static void start(int port){


        try (ServerSocket MySSocket = new ServerSocket(port)){
            System.out.println("Server running on port: "+port);

            while(true){
                Socket clientS = MySSocket.accept();
                handleRequest(clientS);

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static  void main(String[] args){start(8080);}

    public static  void handleRequest(Socket clientSocket){
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream();
        ){
            String line;
            while ((line = in.readLine()) != null && ! line.isEmpty()){
                System.out.println(line);
            }
            String text = "Hello World";
            String response =   "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/plain\r\n"+
                                "Content-Length:" + text.getBytes().length + "\r\n" +
                                "Connection: close\r\n" + "\r\n" +text ;
            out.write(response.getBytes());
            out.flush();
            clientSocket.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
