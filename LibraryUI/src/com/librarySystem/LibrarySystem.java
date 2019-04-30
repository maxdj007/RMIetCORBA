package com.librarySystem;

import java.io.BufferedReader;
import java.io.IOException;

import com.librarySystem.constant.University;
import com.librarySystem.controller.ManagerClientController;
import com.librarySystem.controller.UserClientController;
import com.librarySystem.utility.InputReader;
import com.librarySystem.utility.Utilities;

public class LibrarySystem {

	public static void main(String[] args) {
		
		// Capture the member ID
		BufferedReader reader = InputReader.getReader();
		boolean exit = false;
		do {
		
		System.out.println("Enter your ID:\n");
		String memberID;
		try {
			memberID = reader.readLine();

			// Check if the ID entered is valid
			if (memberID.length() == 8 && Utilities.matchesOrNot("\\d+",memberID.substring(4,8))) {
				if (Utilities.CodeCheck(memberID, true, University.UNIVERSITY1.getCode(), false)
						|| Utilities.CodeCheck(memberID, true, University.UNIVERSITY2.getCode(), false)) {
					// When the member is a user, call the userClient method to
					// proceed
					UserClientController user = new UserClientController();
					user.userClient(memberID);
				} else if (Utilities.CodeCheck(memberID, true, University.UNIVERSITY1.getCode(), true)
						|| Utilities.CodeCheck(memberID, true, University.UNIVERSITY2.getCode(), true)) {
					// When the member is a manager, call the managerClient
					// method
					// to proceed
					ManagerClientController manager = new ManagerClientController();
					manager.managerClient(memberID);
				} else {
					System.out.println("Enter a valid ID!!\n");
				}
			} else {
				System.out.println("Enter a valid ID!!\n");
			}

			System.out.println("Want to exit the application? (Y/N)\n");
			String exitString = reader.readLine();
			exit = (exitString.equals("Y"))? true : false;
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
			
		} while (!exit);
		System.out.println("Application terminated!!");
		
	}

}
