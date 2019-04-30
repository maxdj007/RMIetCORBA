package com.librarySystem.service;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;
import com.librarySystem.rmi.Client;
import com.librarySystem.rmi.LibraryInterface;

public class RMIService implements LibraryService {

	private static Client client;

	static {
		client = new Client();
	}

	public boolean addItem(University university, String managerID, String itemID, String itemName, int quantity) {

		LibraryInterface library = client.getLibrary(university);

		if (library != null) {
			try {
				return library.addItem(managerID, itemID, itemName, quantity);
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			}
		}

		return false;
	}

	public ArrayList<Item> findItem(University university, String userID, String itemName) {

		LibraryInterface library = client.getLibrary(university);
		ArrayList<Item> result = null;
		if (library != null) {
			try {
				result = library.findItem(userID, itemName);
			} catch (RemoteException e) {
				
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

}
