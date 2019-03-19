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
    public static final String XoreoAddress = "0x0400f938b308c49c82f9753e43019b336b7e";
    public static final String OtherAddress = "0x0400e609aaf1d3818b90f7c858a5acb8e5f6";
}