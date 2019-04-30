package com.librarySystem.service;

import java.util.ArrayList;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.corba.Client;
import com.librarySystem.corba.Library;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

public class CorbaService implements LibraryService {

	private static Client client;

	static {
		client = new Client(new String[]{Constants.ORBINITIALPORT_KEY, String.valueOf(Constants.CORBA_PORT)
				, Constants.ORBINITIALHOST_KEY, Constants.HOSTANAME});
	}

	public boolean addItem(University university, String managerID, String itemID, String itemName, int quantity) {

		Library library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.addItem(managerID, itemID, itemName, quantity);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return false;
	}

	public ArrayList<Item> findItem(University university, String userID, String itemName) {

		Library library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				String reply = library.findItem(userID, itemName);
				result = Utilities.getItemsFromReply(reply);
				System.out.println(result.size());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

}
