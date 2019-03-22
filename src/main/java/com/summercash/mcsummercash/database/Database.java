package com.summercash.mcsummercash.database;

import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;
import java.io.*;

// Database - The class used to interface with the Minecraft username/summercash address database
public class Database {

    public Database() throws IOException {
        // Initialize the database
        Options options = new Options();
        options.createIfMissing(true);
        File dbFile = new File("./data/usernames");
        
        // Open the database
        DB usernameDB = factory.open(dbFile, options);

        try {
            // Put something
            byte[] key = bytes("xoreo");
            byte[] value = bytes("https://github.com/xoreo");
            usernameDB.put(key, value);

            // Get something
            byte[] getKey = bytes("xoreo1");
            String xoreo = asString(usernameDB.get(getKey));
            System.out.println("read from db: " + xoreo);
        } finally {
            // Close the database
            usernameDB.close();
        }
    }
    
}