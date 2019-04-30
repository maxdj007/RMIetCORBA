package com.librarySystem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.librarySystem.constant.Constants;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

public class LibraryImpl extends UnicastRemoteObject implements LibraryInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4608161339038485757L;

	public LibraryImpl() throws RemoteException {
		super();
	}

	private static HashMap<String, Item> map;

	@Override
	public synchronized boolean addItem(String managerID, String itemID, String itemName, int quantity) {

		Item item = null;
		boolean result = false;

		// When the item is already present in the inventory, increase its
		// quantity
		if (Utilities.getUniversity(itemID).getCode().equals(Constants.UNIVERSITY.getCode()) && itemID.length() == 7
				&& quantity >= 0) {
			if (map == null) {
				item = new Item();
				map = new HashMap<>();
				item.setID(itemID);
				item.setName(itemName);
				item.setQuantity(quantity);
				map.put(itemID, item);
				result = true;
			} else if (map.containsKey(itemID)) {
				item = map.get(itemID);
				item.setName(itemName);
				item.setQuantity(item.getQuantity() + quantity);
				map.put(itemID, item);
				result = true;
			}
			// When the item is not present in the inventory, add an entry in
			// the
			// HashMap
			else {
				item = new Item();
				item.setID(itemID);
				item.setName(itemName);
				item.setQuantity(quantity);
				map.put(itemID, item);
				result = true;
			}
		} else {
			result = false;
		}
		if(result){
			System.out.println("Item " + itemID + "-" + itemName + " added");
		} else {
			System.out.println("Item " + itemID + "-" + itemName + " could not be added");
		}
		return result;
	}

	@Override
	public synchronized ArrayList<Item> findItem(String userID, String itemName) {
		// If the item name matches the one entered by the user then it is added
		// to the array list
		ArrayList<Item> ResultList = new ArrayList<>();
		if (map != null) {
			for (Entry<String, Item> value : map.entrySet()) {
				if (value.getValue().getName().equals(itemName)) {
					ResultList.add(value.getValue());
				}
			}
		}
		if(!ResultList.isEmpty()){
			System.out.println("Results found");
		} else {
			System.out.println("Results not found");
		}
		return ResultList;
	}
}
