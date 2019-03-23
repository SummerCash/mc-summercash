package com.summercash.mcsummercash.database;

import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;
import java.io.*;

import com.summercash.mcsummercash.common.Common;

// Database - The class used to interface with the Minecraft username/summercash address database
public class Database {

    // Setup the class
    private Options options;
    private DB usernameDB;
    private File dbFile;

    private boolean isOpen;

    // Database - The constructor
    public Database() throws IOException {
        // Setup the db path
        Common common = new Common();
        common.CreateDirIfDoesNotExist(Common.dbPath);
        String path = Common.dbPath + "username";
        dbFile = new File(path);

        // Setup the database options
        options = new Options();
        options.createIfMissing(true);

        isOpen = false;
    }

    // Open - Open the username database
    public void Open() throws IOException {
        // Open the database
        usernameDB = factory.open(dbFile, options);
        isOpen = true;
    }

    // GetAddress - Get an address given a Minecraft username
    public String GetAddress(String username) {
        if (!isOpen) {
            return "";
        } // Check that the database is open

        // Init the key
        byte[] key = bytes(username);

        // Get the value & check not null
        byte[] addressBytes = usernameDB.get(key);
        if (addressBytes != null) {
            return asString(addressBytes);
        }

        return null;
    }

    // PrintDatabase - Print the contents of the database
    public void PrintDatabase() {
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
            try {
                iterator.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
 
    // PutAddress - Put a username-address pair into the database
    public void PutAddress(String username, String address) {
        if (!isOpen) { return; } // Check that the database is open

        // Put the username (key) and address (value) into the database
        byte[] key = bytes(username);
        byte[] value = bytes(address);
        usernameDB.put(key, value);
    }

    // Close - Close the database
    public void Close() throws IOException {
        usernameDB.close();
        isOpen = false;
    }

}