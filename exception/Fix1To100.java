package exception;

import util.FileIO;

// Fixes exceptions inside FileIO.java
public class Fix1To100 {

    void fix1(int ExceptionNo) {
        System.out.println("Altering baseprice to 18445..");
        FileIO.newbasePrice = 18445;

    }

    // Fixes wrong filename;
    void fix2(int ExceptionNo) {
        System.out.println("Altering filename to FordZTW.txt (...)");
        FileIO.newfileName = "FordZTW.txt";
    }

}
