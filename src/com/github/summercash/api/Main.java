package com.github.summercash.api;

class Main {
    public static void main(String[] args) {
    	Transaction req = new Transaction();
    	String res = req.NewTransaction("0x040028d536d5351e83fbbec320c194629ace", "0x0400bb6659813faa43c57e8799c9e9806b2b", 5.0);
    }
}