package com.db.edu.server.storage;

import com.db.edu.server.entity.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader implements Reader {
    private final String HISTORY_FILE_NAME;

    private static final Logger log = LoggerFactory.getLogger(CustomFileReader.class);

    public CustomFileReader() {
        HISTORY_FILE_NAME = "history.txt";
    }

    @Override
    public List<String> read() {
        return readSpecificRoom("");
    }

    @Override
    public List<String> readSpecificRoom(String room) {
        List<String> result = new ArrayList<>();
        try {

            File file = new File(HISTORY_FILE_NAME);
            checkExist(file);

            BufferedReader br = new BufferedReader(new FileReader(HISTORY_FILE_NAME));

            String line = br.readLine();

            while (line != null) {
                if (line.startsWith(room)) {
                    result.add(line);
                }
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public void checkExist(File file) throws IOException {
        if (!file.exists()){
            Printer.print("Create history file ...");
            if (!file.createNewFile()) {
                throw new IOException("Cannot create file history.txt, but it doesn't exist");
            }
            log.info("Created history file.");
        }
    }
}
