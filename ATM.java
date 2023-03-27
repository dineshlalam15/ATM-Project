package ATM_Project;

import java.io.IOException;
import java.util.*;

public class ATM {
	public static void main(String [] args) throws IOException{
		OptionMenu optionMenu = new OptionMenu();
		introduction();
		optionMenu.mainMenu();
	}
	
	public static void introduction() {
		System.out.println("Welcome to ATM Project!");
	}
}
