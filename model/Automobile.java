package model;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Automobile implements Serializable {

	private float basePrice;
	private String name, make, model;
	private ArrayList<OptionSet> optionSet;

	// private LinkedHashMap<String, Automobile> map;

	// --------> Constructors <--------//
	public Automobile() {
		name = "";
		basePrice = 0;
		make = "";
		model = "";
		optionSet = null;
		// map = new LinkedHashMap<>();
		optionSet = new ArrayList<OptionSet>();

	}

	// optionSet refered to this.optionSet
	public Automobile(String name, float basePrice) {

		// map = new LinkedHashMap<>();
		optionSet = new ArrayList<OptionSet>();
		this.name = name;
		this.basePrice = basePrice;
	}

	// optionSet refered to parameter optionSet
	public Automobile(int optSetSize, String name, float basePrice, ArrayList<OptionSet> optionSet) {

		// map = new LinkedHashMap<>();
		optionSet = new ArrayList<OptionSet>();
		this.name = name;
		this.basePrice = basePrice;

	}

	// --------> Getters <--------//
	public String g_Name() {
		return name;
	}

	public float g_Baseprice() {
		return basePrice;
	}

	public String g_Make() {
		return make;
	}

	public String g_Model() {
		return model;
	}

	public String g_OptionChoiceName(String setName) {
		String optionName = null;
		for (int i = 0; i < optionSet.size(); i++) {
			if (setName.equalsIgnoreCase(optionSet.get(i).g_Name())) {
				optionName = optionSet.get(i).g_OptionChoice().g_Name();
				return optionName;
			}
		}
		return null;
	}

	public int g_OptionChoicePrice(String setName) {
		int optionPrice = 0;
		for (int i = 0; i < optionSet.size(); i++) {
			if (setName.equalsIgnoreCase(optionSet.get(i).g_Name())) {
				optionPrice = (int) optionSet.get(i).g_OptionChoice().g_Price();
			}
		}
		return optionPrice;
	}

	public int g_TotalPrice() {
		int totalPrice = 0;
		for (int i = 0; i < optionSet.size(); i++) {
			totalPrice += optionSet.get(i).g_OptionChoice().g_Price();
		}
		return totalPrice;
	}

	// public OptionSet.Option g_Opt(int i, int k) { return; }
	// opt
	// public OptionSet.Option[] g_Opt(int i) { return OptionSet.Option opt[i]; }

	// --------> Setters <--------//
	public void s_Name(String name) {
		this.name = name;
	}

	public void s_BasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public void s_make(String make) {
		this.make = make;
	}

	public void s_model(String model) {
		this.model = model;
	}

	public void s_OptionSet(ArrayList<OptionSet> optionSet) {
		this.optionSet = optionSet;
	}

	public synchronized void s_OptionSetName(int x, String name) {
		optionSet.get(x - 1).s_Name(name);
	}

	public void s_OptSetArr(int x, OptionSet set) {
		return;
	}

	public void s_OptSetOptions(int x, Option choice) {
		return;
	}

	public void s_OptSetOption(int x, int y, Option choice) {
		return;
	}

	public void s_OptSetOption(int x, int y, String name, float price) {
		return;
	}

	// User interactor use
	public synchronized void s_OptionChoiceName(String setName, String optionName) {
		for (int i = 0; i < optionSet.size(); i++) {
			if (setName.equalsIgnoreCase(optionSet.get(i).g_Name())) {
				optionSet.get(i).s_OptionChoice(optionName);
				break;
			}
		}
	}

	// -------> Actions
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Automotive Name: ").append(name).append("Base Price: ").append(basePrice);
		String str = stringBuffer.toString();
		return str;
	}

	public void print() {
		System.out.println(toString());
		for (int i = 0; i < optionSet.size(); ++i)
			optionSet.get(i).print();
	}

	// --------> Methods for: adding, deleting, finding <--------//
	// Parser use
	public synchronized void addOption(String optionSetName, String optionName, float price) {
		Option opt = new Option(optionName, price);
		for (int i = 0; i < optionSet.size(); i++) {

			if (optionSetName.equals(optionSet.get(i).g_Name())) {
				optionSet.get(i).addOption(opt);
				break;

			}
		}

	}

	// Parser use
	public synchronized void addOptionSet(String str) {
		OptionSet optSet = new OptionSet(str);
		optionSet.add(optSet);
	}

	// findOption() To find Option in specified OptionSet
	// Parser use
	public Option findOption(String optionSetName, String optionName) {
		for (int i = 0; i < optionSet.size(); ++i) {
			if (optionSet.get(i).g_Name().equalsIgnoreCase(optionSetName))
				return optionSet.get(i).findOption(optionName, optionSet.size());
		}
		return null;
	}

	// findOptionSet() To find OptionSet using its name
	// Parser use
	public OptionSet findOptionSet(String optionSetName) {
		for (int i = 0; i < optionSet.size(); ++i) {
			if (optionSet.get(i).g_Name().equalsIgnoreCase(optionSetName)) {
				optionSet.get(i).print();
				return optionSet.get(i);
			}
		}
		return null;
	}

	// deleteOption() Deletes the specified Option from the optionSetArray
	public synchronized boolean deleteOption(String optionSetName, String optionName) {
		OptionSet opset = findOptionSet(optionSetName);
		if (opset != null)
			return opset.deleteOption(optionName);

		return false;
	}

	// deleteOptionSet() Deletes the specified opset from OptionSet
	public synchronized boolean deleteOptionSet(String optionSetName) {
		OptionSet opset = findOptionSet(optionSetName);
		if (opset == null)
			return false;
		for (int i = 0; i < optionSet.size(); i++) {
			if (optionSetName.equals(optionSet.get(i).g_Name())) {
				optionSet.remove(i);
				break;
			}
		}
		return true;

	}

}
