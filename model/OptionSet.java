package model;

import java.io.*;
import java.util.ArrayList;

//Set of OptionSet in each Model and respective Options can be saved in an ArrayList.

public class OptionSet implements Serializable {

    private String name;
    private ArrayList<Option> choices;
    private Option myChoice;

    // --------> Constructors <--------//

    protected OptionSet() {
        choices = null;
        myChoice = null;
        name = "";
    }

    protected OptionSet(String name) {
        choices = new ArrayList<Option>(); // Popukating only Opset
        myChoice = new Option();
        this.name = name;
    }

    // --------> Setter(s) <--------//
    protected void s_Name(String name) {
        this.name = name;
    }

    protected void s_Options(ArrayList<Option> choices) {
        this.choices = choices;
    }

    public void s_OptionChoice(String optionName) {
        for (int i = 0; i < choices.size(); i++) {
            if (choices.get(i).g_Name().equals(optionName)) {
                myChoice = choices.get(i);
            }

        }
    }

    // --------> Getter(s) <--------//
    protected String g_Name() {
        return name;
    }

    protected ArrayList<Option> g_Options() {
        return this.choices;
    }

    protected Option g_Options(int index) {
        return choices.get(index);
    }

    protected Option g_OptionChoice() {
        return myChoice;
    }

    // --------> Finders, add, delete <--------//
    protected void addOption(Option option) {
        choices.add(option);
    }

    protected Option findOption(String optionName, int optionSize) {
        for (int i = 0; i < optionSize; ++i) {
            if (choices.get(i).g_Name().equalsIgnoreCase(optionName)) {
                choices.get(i).print();
                return choices.get(i);
            }
        }
        return null;
    }

    protected boolean deleteOption(String OptionName) {
        for (int i = 0; i < choices.size(); i++) {
            if (choices.get(i).g_Name().equalsIgnoreCase(OptionName)) {
                choices.remove(i);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OptionSet Name: ").append(name);
        String str = stringBuffer.toString();
        return str;
    }

    // --> Prints objects attributes
    protected void print() {
        System.out.println(toString());
        for (int i = 0; i < choices.size(); ++i)
            choices.get(i).print();
    }

}
