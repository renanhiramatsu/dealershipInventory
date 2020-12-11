
package server;

import adapter.*;
import model.Automobile;
import util.FileIO;
import java.util.Properties;

public class BuildCarModelOptions extends ProxyAutomobile {

	////////// PROPERTIES //////////

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;

	private int state = WAITING;
	private Automobile autos;

	////////// CONSTRUCTORS //////////

	public BuildCarModelOptions(Automobile autos) {
		this.autos = autos;
	}

	public Automobile g_Autos() {
		return autos;
	}

	public int g_State() {
		return state;
	}

	public void s_State(int state) {
		this.state = state;
	}

	////////// INSTANCE METHODS //////////

	public Object processRequest(Object obj) {
		Object toClient = null;

		if (state == REQUEST_BUILD_AUTO) {
			FileIO fileIO = new FileIO();
			Automobile automobile = fileIO.buildAutoFromProp((Properties) obj);
			autos.addToLHM(automobile.g_Name(), automobile);
			toClient = "Automobile object successfully added to database\n" + "Press enter key to return to main menu";
		} else if (state == REQUEST_CONFIGURE_AUTO) {
			String[] names = autos.g_AutomobileNames().split("\n"); // Get Names from LHM
			String targetName = null;
			for (String name : names) {
				if (name.startsWith(((Integer) obj).toString() + ".")) {
					targetName = name.substring(name.indexOf(".") + 2);
					break;
				}
			}
			toClient = autos.g_Automobile(targetName);
		} else {

		}

		this.state = WAITING;

		return toClient;
	}

	public String defaultResponse(int i) {
		StringBuilder output = new StringBuilder();

		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output.append("Upload a file to create an Automobile\nPlease enter the file name (including extension)\n");
		} else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output.append("Select an Automobile from the following list to configure: \n");
			output.append("0. Close connection to server\n");
			output.append(autos.g_AutomobileNames());
		} else {
			output.append("Invalid request");
		}

		return output.toString();
	}

}
