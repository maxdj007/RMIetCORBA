package com.librarySystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.DSImplementation;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.service.CorbaService;
import com.librarySystem.service.LibraryService;
import com.librarySystem.service.RMIService;
import com.librarySystem.utility.InputReader;
import com.librarySystem.utility.Utilities;

public class UserClientController {

private LibraryService service;
	
	public UserClientController(){
		if(Constants.DS_IMPLEMENTATION.equals(DSImplementation.RMI)){
			service = new RMIService();
		} else {
			service = new CorbaService();
		}
	}
	
	public void userClient(String userID) throws IOException {
		BufferedReader reader = InputReader.getReader();
		String itemName = null;
		boolean logout = false;
		University university = null;

		// Check the university of the user.
		if (Utilities.CodeCheck(userID, false, University.UNIVERSITY1.getCode(), false)) {
			university = University.UNIVERSITY1;
		} else if (Utilities.CodeCheck(userID, false, University.UNIVERSITY2.getCode(), false)) {
			university = University.UNIVERSITY2;
		}
	
		do {

			System.out.println(
					"\nSelect the action to be performed:\n1.Find an item.\n2.Logout.\n ");
			int selection = 0;
			try{
			selection = Integer.valueOf(reader.readLine());
			switch (selection) {
			case 1:
				System.out.println("Enter the name of the item to be searched:\n");
				itemName = reader.readLine();
				// Call RMIService method
				ArrayList<Item> itemsfound = service.findItem(university, userID, itemName);
				if (!itemsfound.isEmpty()) {
					System.out.println("Items found: ");
					for (int i = 0; i < itemsfound.size(); i++) {
						//System.out.println(itemsfound.get(i).getID() + " | " + itemsfound.get(i).getName() + " | "
							//	+ itemsfound.get(i).getQuantity());
						System.out.println(itemsfound.get(i).getID() + "  "
								+ itemsfound.get(i).getQuantity());
					}
				} else {
					System.out.println("No Such Item Found!!\n");
				}
				break;

			case 2:
				logout = true;
				break;
				
			default:
				System.out.println("Enter a valid choice!!\n");
				break;
			}
		}catch(NumberFormatException |StringIndexOutOfBoundsException e){
			System.out.println("Enter a valid number!!");
		}
		}while (!logout);
	}

}