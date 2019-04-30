package com.librarySystem.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.DSImplementation;
import com.librarySystem.constant.University;
import com.librarySystem.service.CorbaService;
import com.librarySystem.service.LibraryService;
import com.librarySystem.service.RMIService;
import com.librarySystem.utility.InputReader;
import com.librarySystem.utility.Utilities;

public class ManagerClientController {
	
	private LibraryService service;
	
	public ManagerClientController(){
		if(Constants.DS_IMPLEMENTATION.equals(DSImplementation.RMI)){
			service = new RMIService();
		} else if(Constants.DS_IMPLEMENTATION.equals(DSImplementation.CORBA)){
			service = new CorbaService();
		}
	}
	
	public void managerClient(String managerID) throws IOException {
		BufferedReader reader = InputReader.getReader();
		String itemID = null;
		String itemName = null;
		boolean logout = false;
		int quantity = 0;
		University university = null;
		if (Utilities.CodeCheck(managerID, false, University.UNIVERSITY1.getCode(), false)) {
			university = University.UNIVERSITY1;
		} else if (Utilities.CodeCheck(managerID, false, University.UNIVERSITY2.getCode(), false)) {
			university = University.UNIVERSITY2;
		}

		do {
			System.out.println(
					"\nSelect the action to be performed:\n1.Add an item.\n2.Logout\n");
			int selection = Integer.valueOf(reader.readLine());
			try{
			switch (selection) {
			case 1:
				System.out.println("\nEnter the ID for the item to be Added: ");
				itemID = reader.readLine();
				System.out.println("Enter the name for the item to be Added: ");
				itemName = reader.readLine();
				System.out.println("Enter the number items to be added: ");
				quantity = Integer.valueOf(reader.readLine());
				if(Utilities.matchesOrNot("\\d+",itemID.substring(3,7))){
					if (service.addItem(university, managerID, itemID, itemName, quantity)) {
						System.out.println("The item was added successfully!!\n");
					} else {
						System.out.println("The item could not be added!!\n");
					}
				}else{
					System.out.println("Enter a valid item ID");
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
		} while (!logout);
	}

}
