package com.librarySystem.rmi;

import java.rmi.Remote;
import java.util.ArrayList;

import com.librarySystem.model.Item;

public interface LibraryInterface extends Remote  {

	public boolean addItem(String managerID, String itemID, String itemName, int quantity)
			throws java.rmi.RemoteException;

	public ArrayList<Item> findItem(String userID, String itemName) throws java.rmi.RemoteException;
}
