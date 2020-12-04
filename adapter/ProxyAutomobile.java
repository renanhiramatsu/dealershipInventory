package adapter;

import util.FileIO;
import java.util.LinkedHashMap;
import java.util.Set;

import model.*;

public abstract class ProxyAutomobile {

    private static Automobile a1;
    private static LinkedHashMap<String, Automobile> map = new LinkedHashMap<String, Automobile>();

    // To build instance of Automobile
    public void BuildAuto(String filename) {
        boolean isNull = true;
        while (isNull) {
            try {
                FileIO file = new FileIO();
                a1 = file.readFile(filename);
                isNull = false;
            } catch (Exception e) {
                filename = FileIO.newfileName;
            }

            // Stores each automobile in a LinkedHashMap, with its model name as the key
            map.put("firstmodel", a1);
            System.out.println("Model " + map.get("firstmodel") + " added.");

        }
    }

    // Searches and prints the properties(Name, BasePrice) of given Automobile
    public void printAuto(String Modelname) {
        System.out.println("Name: " + a1.g_Name());
        System.out.println("Base Price: " + a1.g_Baseprice());
        System.out.println("Transmission: " + a1.g_OptionChoiceName("Transmission"));
        System.out.println("Color: " + a1.g_OptionChoiceName("color"));
    }

    // Searches the Model for a given OptionSet and sets the name of OptionSet to
    // newName.
    public void updateOptionSetName(String Modelname, String OptionSetName, String newName) {

        // a1.s_OptSetName(a1.g_Index(), newName);
    }

    // searches the Model for a given OptionSet and Option name, and sets the price
    // to newPrice.
    public void updateOptionPrice(String Modelname, String Optionname, String StringOption, float newprice) {
        a1.s_BasePrice(newprice);

    }

    // >> Implementation for EditOptionsMethods << //

    // Will set the Option for given OptionSet
    public synchronized void s_Option(String optsetName, String desiredOption) {
        System.out.println("Desired option: " + desiredOption + " for " + optsetName);
        a1.s_OptionChoiceName(optsetName, desiredOption);
    }

    public synchronized void printMapKeys() {
        Set<String> keys = map.keySet();
        for (String i : keys) {
            System.out.println("Printing keys.. ");
            System.out.println(i + " = " + map.get(i));
        }

    }

}
