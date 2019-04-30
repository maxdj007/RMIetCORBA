package com.librarySystem.service;

import java.util.ArrayList;

import com.librarySystem.constant.University;
import com.librarySystem.model.Item;

public interface LibraryService {
	
	public boolean addItem(University university, String managerID, String itemID, String itemName, int quantity);

	public ArrayList<Item> findItem(University university, String userID, String itemName);
}
