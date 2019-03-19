package com.summercash.mcsummercash.common;

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
    public static final String XoreoAddress = "0x040040dc9d49726e5c9d21a3ee3da7e2be5e";
    public static final String OtherAddress = "0x04015d0c9699aa74db4edc851e322ae9b028";   
}