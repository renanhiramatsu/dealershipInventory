
package client;

import model.*;
import java.util.*;

public class SelectCarOptions {

	////////// PROPERTIES //////////
	private Scanner in = new Scanner(System.in);

	////////// CONSTRUCTORS //////////

	public SelectCarOptions() {

	}

	// Getters & Setters

	public Scanner g_In() {
		return in;
	}

	public void s_In(Scanner in) {
		this.in = in;
	}

	////////// INSTANCE METHODS //////////

	public void configureAuto(Object obj) {

		((Automobile) obj).print();
		System.out.printf("Please enter your Option choice following the " + "order of OptionSet.\nOne at a time\n");

		for (int i = 0; i < ((Automobile) obj).g_OptsetSize(); ++i) {
			String optionSetName = ((Automobile) obj).g_OptionSetName(i);
			((Automobile) obj).s_OptionChoiceName(optionSetName, in.nextLine());
		}
		System.out.printf("The automobile you configure: \n");
		((Automobile) obj).print();
		System.out.printf("Total price: %.2f\n", ((Automobile) obj).g_TotalPrice());

	}

	public void print() {
		System.out.printf("in : %s\n", in.toString());
	}

}
