/* ==== DOCUMENTATION ====
Author: Renan Silva Hiramatsu
Lab 1
This application will read from a formatted file, filling up all of the classes object with pre defined attributes
so that it can create a car object with desired options (such as desired color, transmission, brake type and so on).
It will serialize the created object and save it to a file named "auto.ser", then deserialize and printing to the screen
before and after the serialization.
*/

package driver;

import model.Automobile;
import util.FileIO;

public class Driver1 {
	public static void main(String[] args) {
		FileIO file = new FileIO();
		try {
			Automobile FordZTW = file.readFile("FordZTW.txt");
			System.out.println("Printing attributes before serialization");
			FordZTW.print();

			// Serializing Automobile object
			file.serializeAuto(FordZTW);

			// Deserializing Automobile object and reading into memory
			Automobile newFordZTW = file.deserializeAuto("auto.ser");
			System.out.println("\nNew Automobile attributes are:");
			newFordZTW.print();

		} catch (Exception e) {
			e.toString();
		}

	}
}
