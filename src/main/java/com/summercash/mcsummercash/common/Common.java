package com.summercash.mcsummercash.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Common - Common methods & data to be accessed from the entire project
public class Common {
    // PROVIDER - The ip address of the go-summercash RPC node
    public static final String PROVIDER = "localhost";
    // public static final String PROVIDER = "192.168.1.253";
    // public static String PROVIDER = "108.41.124.60"; // @dowlandaiello's node

    // PORT - The port for the go-summercash RPC node
    public static final String PORT = "8081";

    // LOGGING - Should RPC logging be on or of?
    public static final boolean LOGGING = true;

    // Some addresses that have a balance
    public static final String TestingAddress1 = "0x040021c2524506b1d724bbd326ca8049f5fa";
    public static final String TestingAddress2 = "0x0400fc65fa379d133abe75dda1d6e4dd5a49";

    public static final String dbPath = "data/";

    public static void CreateDirIfDoesNotExist(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    public static String ParseMCUsername(String rawUsername) {
        return rawUsername.split("name=")[1].replace("}", "");
    }
}