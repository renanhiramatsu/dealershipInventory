/* ==== DOCUMENTATION ====
Author: Renan Silva Hiramatsu
This second driver will test new features such as custom made exceptions in the new package "exception".
We also have defined a set of classes and methods to make an API, so we can also protect data.
*/

package driver;

import adapter.*;

public class Driver2 {
    public static void main(String[] args) {

        String optionSetName = "OptionSetName";
        String modelName = "FordWagonZTW";
        String newOptionSetName = "NewOptionSetName";
        String fileName = "FordZT.txt";
        String optionName = "Optionname";
        float newBasePrice = 15000;

        CreateAuto a1 = new BuildAuto();
        a1.BuildAuto(fileName);
        a1.printAuto(modelName); // API Designed for the future

        // Update one of OptionSet's name for Auto instance created above
        UpdateAuto a2 = new BuildAuto();
        a2.updateOptionSetName(modelName, optionSetName, newOptionSetName);
        a2.updateOptionPrice(modelName, optionSetName, optionName, newBasePrice);

        System.out.println("After updating baseprice...");
        a1.printAuto(modelName); // This will test if we updated basePrice to newBasePrice}

    }

}
