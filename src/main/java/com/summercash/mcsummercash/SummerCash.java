package com.summercash.mcsummercash;

import com.summercash.mcsummercash.database.Database;

// SummerCash - The project's main function. This doesn't have much of a purpose
public class SummerCash {

    public static void main(String[] args) throws Exception {
        Database db = new Database();
        db.Open();

        db.PutAddress("xoreo", "https://github.com/xoreo/");

        db.PrintDatabase();

        db.Close();
    }
}
