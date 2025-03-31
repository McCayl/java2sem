package com.mccayl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class FileReaderThread extends Thread {
    private final StringBuilder content = new StringBuilder();

    @Override
    public void run() {
        try {
            Path path = Paths.get(System.getProperty("user.dir"), "lab6", "src", "MainEx3.java");
            System.out.println("Trying to read: " + path.toAbsolutePath());

            if (!Files.exists(path)) {
                System.out.println("Файл не найден!");
                return;
            }

            List<String> lines = Files.readAllLines(path);
            lines.forEach(line -> {
                content.append(line).append("\n"); // Сохраняем содержимое
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return content.toString();
    }
}

public class MainEx3 {
    public static void main(String[] args) throws InterruptedException {
        FileReaderThread reader = new FileReaderThread();
        reader.start();
        reader.join();

        System.out.println(reader.getContent());
    }
}
