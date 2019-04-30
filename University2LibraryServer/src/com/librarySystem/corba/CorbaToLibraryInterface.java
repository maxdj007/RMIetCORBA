package com.librarySystem.corba;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.omg.CORBA.ORB;

import com.librarySystem.rmi.LibraryImpl;
import com.librarySystem.rmi.LibraryInterface;
import com.librarySystem.model.Item;
import com.librarySystem.utility.Utilities;

public class CorbaToLibraryInterface extends LibraryPOA {
	
	private static LibraryInterface library;
	
	private ORB orb;
	
	static{
		try {
			library = new LibraryImpl();
		} catch (RemoteException e) {
			System.out.println(e.toString());
		}
	}
	
	public void setORB(ORB orb_val) {
	   orb = orb_val; 
	}

	@Override
	public boolean addItem(String managerID, String itemID, String itemName, int quantity) {
		try {
			return library.addItem(managerID, itemID, itemName, quantity);
		} catch (RemoteException e) {
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public String findItem(String userID, String itemName) {
		String returnString = null;
		try{
			ArrayList<Item> items=library.findItem(userID, itemName);
			returnString = Utilities.getReplyStringFromList(items);
		}catch(RemoteException e){
			System.out.println(e.toString());
		}
		return returnString;
	}

	@Override
	public void shutdown() {	
		orb.shutdown(true);
	}

}
