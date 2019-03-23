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
    public static final String XoreoAddress = "0x0401baf5eee418d8d269624b83f872d38b35";
    public static final String OtherAddress = "0x04010dfbaac4ab79ec130d84ed47249f4ac4";

    public static final String dbPath = "data/";

    public void CreateDirIfDoesNotExist(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }
}