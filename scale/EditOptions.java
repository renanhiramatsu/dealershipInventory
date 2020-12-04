package scale;

import adapter.BuildAuto;
import adapter.EditOptionsMethods;
import adapter.ProxyAutomobile; // To use LHM

// --> Each operation will be running in its own thread.
// --> Synchronize all methods in this class.

// --> Basically follow how Hello, Coffe works

// This class will edit Options for a given model in its own thread.
public class EditOptions extends Thread {

    EditOptionsMethods e1;
    int test1 = 0;

    public EditOptions() {
        e1 = new BuildAuto();
        run();
        System.out.println("test1: " + test1);
    }

    public void run() {
        System.out.println("Thread running.");
        add();
        subtract();

    }

    public void s_Option(String optSetName1, String desiredOption1, String optSetName2, String desiredOption2) {
        e1.s_Option(optSetName1, desiredOption1);
        e1.s_Option(optSetName2, desiredOption2);
    }

    // TODO: Use of LHM map from proxyAutomobile in here.

    void add() {
        for (int k = 0; k < 100000; k++) {
            test1++;

        }
    }

    void subtract() {
        for (int k = 0; k < 100000; k++) {
            test1--;

        }
    }

}
