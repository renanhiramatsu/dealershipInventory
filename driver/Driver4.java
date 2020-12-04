
/* ==== DOCUMENTATION ====
Author: Renan Silva Hiramatsu
In this 4th Lab, 
TODO: 
*/
package driver;

import adapter.*;
import exception.AutoException;
import scale.EditOptions;

public class Driver4 {
    public static void main(String[] args) throws AutoException {

        CreateAuto c1 = new BuildAuto();
        c1.BuildAuto("FordZTW.txt");

        UpdateAuto u1 = new BuildAuto();
        u1.updateOptionPrice("Modelname", "Optionname", "StringOption", 18445);

        // I will have to update both e1 and e2 and check if there's no corrpution
        // occurring.
        EditOptions e1 = new EditOptions();

        // e1.s_Option("transmission", "manual", "color", "Infra-Red Clearcoat");

        // System.out.println("Print Auto below: ");
        // c1.printAuto("firstmodel");
    }

}
