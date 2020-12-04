
/* ==== DOCUMENTATION ====
Author: Renan Silva Hiramatsu
In this 3rd Lab, we increase the performance of the functionality that we had before to a better performance.
We use Data Structures to improve on performance, by changing Arrays into ArrayLists and having the model of Automobile stored
in a LinkedHashMap.
*/
package driver;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

//write a driver program to read your text input file, populate an instance of
//Automobile class and print the OptionSet's and their respective options.
public class Driver3 {
    public static void main(String[] args) throws AutoException {

        FileIO f = new FileIO();
        Automobile FordZTW = f.readFile("FordZTW.txt");

        FordZTW.print();
    }

}
