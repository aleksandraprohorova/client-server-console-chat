package com.db.edu.server;

import com.db.edu.server.storage.CustomFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static java.lang.System.lineSeparator;

public class FileReaderTest {

    public static final String sep = lineSeparator();
    private BufferedReader reader;
    private CustomFileReader customFileReader = new CustomFileReader();
    File file = new File("history.txt");
    private DataInputStream input;
    ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    public FileReaderTest() {
    }

    @BeforeEach
    public void SetUp() {
        reader = mock(BufferedReader.class);
        input = new DataInputStream(new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8)));
        captureSysout();
    }

    @Test
    public void TestRead() {
        assertEquals(customFileReader.read(), customFileReader.readSpecificRoom(""));
    }

    @Test
    public void TestFileWriterFileNotExist() throws IOException {
        file = mock(File.class);
        when(file.exists()).thenReturn(false);
        customFileReader.checkExist(file);
        assertEquals(OUT.toString(), "Create history file ..." + sep);
    }

    private void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }
}
