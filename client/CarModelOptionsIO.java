
package client;

import java.io.*;
import java.util.*;

import model.Automobile;
import util.FileIO;

public class CarModelOptionsIO {

	////////// PROPERTIES //////////
	private FileIO fIO;

	////////// CONSTRUCTORS //////////

	public CarModelOptionsIO() {
		fIO = new FileIO();
	}

	////////// GETTERS & SETTERS //////////

	public FileIO g_FileIO() {
		return fIO;
	}

	public void s_FileIO(FileIO fIO) {
		this.fIO = fIO;
	}

	////////// INSTANCE METHODS //////////

	public Properties loadPropsFile(String fname) {
		Properties properties = fIO.buildProperties(fname);
		return properties;
	}

	public Automobile loadTextFile(String fname) throws Exception {
		Automobile auto = fIO.readFile(fname);
		return auto;
	}

	public void print() {
		System.out.printf("fileIO : \n");
		fIO.print();
	}
}
