package com.db.edu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessageHandler {
    private final BufferedReader reader;
    private final DataInputStream input;
    private final DataOutputStream out;

    public MessageHandler(BufferedReader bufferedReader, DataInputStream in, DataOutputStream out) {
        this.input = in;
        this.out = out;
        this.reader = bufferedReader;
    }

    public void handle() throws IOException, InterruptedException {
        while (true) {
            String command = reader.readLine();
            Printer.print(command);
            out.writeUTF(command);
            out.flush();
            Thread.sleep(1000);
            Printer.print(input.readUTF());
        }
    }
}