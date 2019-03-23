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

    // OpenDatabase - Open the username database
    private void OpenDatabase() throws IOException {
        // Open the database
        usernameDB = factory.open(dbFile, options);
    }

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
    }

    // GetAddress - Get an address given a Minecraft username
    public String GetAddress(String username) {
        // Init the key
        byte[] key = bytes(username);

        // Get the value & check not null
        byte[] addressBytes = usernameDB.get(key);
        if (addressBytes != null) {
            return asString(addressBytes);
        }

        return null;
    }
 
    // PutAddress - Put a username-address pair into the database
    public void PutAddress(String username, String address) {
        // Put the username (key) and address (value) into the database
        byte[] key = bytes(username);
        byte[] value = bytes(address);
        usernameDB.put(key, value);
    }

    // Close - Close the database
    public void Close() throws IOException {
        usernameDB.close();
    }
    
}