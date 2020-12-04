package util;

import java.io.*;

import exception.AutoException;
import model.Automobile;

public class FileIO {
    public static String newfileName;
    public static float newbasePrice;

    public Automobile readFile(String fileName) throws AutoException {
        Automobile Automobile = null;
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;

            if (!eof) {
                String bPrice = buff.readLine();
                if (eof) {
                    eof = true;
                } else {
                    float basePrice = Float.parseFloat(bPrice);
                    if (basePrice == 0) {
                        System.out.println("Exception found! Baseprice not specified.");
                        new AutoException(1);
                        basePrice = newbasePrice;
                    }
                    Automobile = new Automobile("Focus Wagon ZTW", basePrice);
                }
            }

            while (!eof) {
                String iterator = buff.readLine();

                if (iterator == null)
                    eof = true;
                else {
                    // One OptionSet at a time, it will populate an array of options
                    String[] iteratorSplit = iterator.split(":");
                    String[] options = iteratorSplit[1].split(","); // options (blue,manual, etc)

                    Automobile.addOptionSet(iteratorSplit[0].trim());

                    for (int i = 0; i < options.length; ++i) {
                        String[] optName = options[i].split(";"); // [0] = name, [1] = price
                        Automobile.addOption(iteratorSplit[0].trim(), optName[0].trim(), Float.parseFloat(optName[1]));
                    }
                }
            }
            buff.close();
        } catch (

        FileNotFoundException fe) {
            System.out.println("Exception! File specified was not found.");
            throw new AutoException(2);
        }

        catch (Exception ie) {
            System.out.println("Error -- " + ie.toString());
        }

        return Automobile;
    }

    // Serialize Automobile Object
    public void serializeAuto(Automobile Automobile) {
        try {
            FileOutputStream fileOut = new FileOutputStream("auto.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Automobile);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in auto.ser");
        } catch (Exception e) {
            System.out.println("Error -- " + e.toString());
        }
    }

    // Deserialize Automobile object
    public Automobile deserializeAuto(String fileName) {
        Automobile Automobile = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Automobile = (Automobile) in.readObject();
            in.close();
            fileIn.close();
            return Automobile;
        } catch (Exception e) {
            System.out.println("Error -- " + e.toString());
        }
        return null;
    }
}