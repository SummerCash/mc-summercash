package com.summercash.mcsummercash.database;

import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;
import java.io.*;

import com.summercash.mcsummercash.common.Common;

// Database - The class used to interface with the Minecraft username/summercash address database
public class Database {

    public Database() throws IOException {
        // Initialize the database
        Options options = new Options();
        options.createIfMissing(true);
        
        // Setup the db path
        Common common = new Common();
        common.CreateDirIfDoesNotExist(Common.dbPath);
        String path = Common.dbPath + "username";
        File dbFile = new File(path);
        
        // Open the database
        DB usernameDB = factory.open(dbFile, options);

        try {
            // Put something
            byte[] key = bytes("bob");
            byte[] value = bytes("https://github.com/" + asString(key));
            usernameDB.put(key, value);

            // Get something
            byte[] getKey = bytes("xoreo");
            String xoreo = asString(usernameDB.get(getKey));
            System.out.println("read from db: " + xoreo);

            // Iterate through the db
            DBIterator iterator = usernameDB.iterator();
            try {
                for (iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
                    String loopKey = asString(iterator.peekNext().getKey());
                    String loopValue = asString(iterator.peekNext().getValue());
                    System.out.println(loopKey + " = " + loopValue);
                }
            } finally {
                // Make sure you close the iterator to avoid resource leaks.
                iterator.close();
            }

        } finally {
            // Close the database
            usernameDB.close();
        }
    }
    
}