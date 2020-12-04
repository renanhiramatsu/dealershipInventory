package exception;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

// Ability to track Error No. and Error MSG
// Enumeration of all possible errors numbers and messages, so we use when AutoException is instantiated
// Log with timestamps in textfile (log.txt)

public class AutoException extends Exception {

    public Errors err;

    public AutoException(int ExceptionNo) {
        fix(ExceptionNo);
    }

    public enum Errors {
        error1(1, "Baseprice not found in the input file."), error2(2, "Wrong filename.");

        private final int no;
        private final String msg;

        Errors(int no, String msg) {
            this.no = no;
            this.msg = msg;
        }
    }

    public void fix(int ExceptionNo) {
        Fix1To100 f1 = new Fix1To100();

        switch (ExceptionNo) {

            case 1:
                f1.fix1(ExceptionNo);
                this.err = Errors.error1;
                logToFile(err.no, err.msg);
                break;
            case 2:
                f1.fix2(ExceptionNo);
                this.err = Errors.error1;
                logToFile(err.no, err.msg);
                break;
        }

    }

    public void logToFile(int error_no, String error_msg) {
        try {
            File file = new File("log.txt");
            PrintWriter output = new PrintWriter(file);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Logging error data into log.txt..");
            output.println("Error number: " + error_no + " Message: " + error_msg + " Date/Time: " + dtf.format(now));
            output.close();
        } catch (FileNotFoundException e) {
        }
    }

}
