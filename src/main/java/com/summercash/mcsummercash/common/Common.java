package com.summercash.mcsummercash.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Common - Common methods & data to be accessed from the entire project
public class Common {
    // PROVIDER - The ip address of the go-summercash RPC node
    public static final String PROVIDER = "localhost";
    // public static String PROVIDER = "108.41.124.60"; // @dowlandaiello's node

    // PORT - The port for the go-summercash RPC node
    public static final String PORT = "8081";

    // LOGGING - Should RPC logging be on or of?
    public static final boolean LOGGING = true;

    // Some addresses that have a balance
    public static final String TestingAddress1 = "0x04000d5c4ff051cf3f2607502356dc30da48";
    public static final String TestingAddress2 = "0x04013f22d843158760739b7bd6093be2ec63";

    public static final String dbPath = "data/";

    public void CreateDirIfDoesNotExist(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }
}