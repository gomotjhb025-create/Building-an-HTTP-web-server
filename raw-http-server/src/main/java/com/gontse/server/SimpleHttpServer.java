package com.gontse.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    public static void main(String[] args){
        int port = 8000;

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

    public static  void handleRequest(Socket clientSocket){
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        ){
            String line;
            while ((line = in.readLine()) != null && ! line.isEmpty()){
                System.out.println(line);
            }
        }catch (IOException e){
            
        }

    }
}
