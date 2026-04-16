package com.gontse.server;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {
    @Test
    void returnHelloWorld() throws  Exception{
        int port = 8081;
        new Thread(() -> SimpleHttpServer.start(port)).start();

        Thread.sleep(200);

        Socket mysocket = new Socket("127.0.0.1",port);

        OutputStream output = mysocket.getOutputStream();
        BufferedReader input = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));

        output.write("GET / HTTP/1.1\r\nHost: localhost\r\n\r\n".getBytes());
        output.flush();

        StringBuilder response = new StringBuilder();
        String line;

        while((line = input.readLine()) != null){
            response.append(line);
        }
        mysocket.close();

        assertTrue(response.toString().contains("Hello World"));
    }

}
