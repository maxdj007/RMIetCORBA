package com.librarySystem.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.corba.CorbaToLibraryInterface;
import com.librarySystem.model.Item;
import com.librarySystem.rmi.LibraryInterface;

public class Utilities {
		
	public static void createDirectoryIfNotExist(String path){
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("Creating dir" + path);
            file.mkdirs();
            
        }
	}

	@SuppressWarnings("unused")
	public static void initRegistry(int PortNumber) throws RemoteException {
		try {
			Registry register = LocateRegistry.getRegistry(PortNumber);
			register.list();
		} catch (RemoteException e) {
			System.out.println("Cannot register to the port");
			Registry register = LocateRegistry.createRegistry(PortNumber);
			System.out.println("New Registery is created");
		}
	}

	public static boolean CodeCheck(String str, boolean clientCheck, String code, boolean isManager) {

		str = (clientCheck) ? str.substring(0, 4) : str.substring(0, 3);
		if (clientCheck) {
			if (!str.equals(code)) {
				if (isManager) {
					if (str.endsWith("M")) {
						return true;
					}
				} else {
					if (str.endsWith("U")) {
						return true;
					}
				}
			}
		} else {
			if (str.equals(code)) {
				return true;
			}
		}

		return false;
	}
	
	public static University getUniversity(String userId){
		if(University.UNIVERSITY1.getCode().equals(userId.substring(0, 3))){
			return University.UNIVERSITY1;
		} else {
			return University.UNIVERSITY2;
		}
	}
	
	public static ArrayList<Item> getItemsFromReply(String message){
		ArrayList<Item> items = new ArrayList<>();
		if(message != null){
			String[] list = message.split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR
					+Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR);
			System.out.println(Arrays.toString(list));
			for(int i = 0; i < list.length; i++){
				String[] s = list[i].split(Constants.ESCAPED_ESCAPE_OPERATOR+Constants.SERVER_MESSAGE_SEPERATOR);
				if(s.length == 3) {
					Item item = new Item(s[0].trim(), s[1].trim(), Integer.valueOf(s[2].trim()));
					items.add(item);
				}
			}
		}
		return items;
	}
	
	public static String getReplyStringFromList(ArrayList<Item> list){
		int i = 0;
		StringBuilder builder = new StringBuilder();
		for(Item item : list){
			builder.append(item.getID())
			.append(Constants.SERVER_MESSAGE_SEPERATOR)
			.append(item.getName())
			.append(Constants.SERVER_MESSAGE_SEPERATOR)
			.append(String.valueOf(item.getQuantity()));
			if(i < list.size()-1){
				builder.append(Constants.SERVER_MESSAGE_DOUBLE_SEPERATOR);
			}
		}
		return builder.toString();	
	}
	
	
	public static void loadLibrary(LibraryInterface library){
		String filePath = Constants.LIBRARY_DISK_PATH + Constants.UNIVERSITY.getCode() + "_data";
		try {
			Utilities.createDirectoryIfNotExist(Constants.LIBRARY_DISK_PATH);
			File file = new File(filePath);
			if(!file.exists()){
				throw new FileNotFoundException("The file was not found");
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			ArrayList<Item> list = Utilities.getItemsFromReply(line);
			for(Item item : list){
				library.addItem(Constants.UNIVERSITY.getCode()+"M0000", item.getID(), item.getName(), item.getQuantity());
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void loadLibrary(CorbaToLibraryInterface library){
		String filePath = Constants.LIBRARY_DISK_PATH + Constants.UNIVERSITY.getCode() + "_data";
		try {
			Utilities.createDirectoryIfNotExist(Constants.LIBRARY_DISK_PATH);
			File file = new File(filePath);
			if(!file.exists()){
				throw new FileNotFoundException("The file was not found");
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			ArrayList<Item> list = Utilities.getItemsFromReply(line);
			for(Item item : list){
				library.addItem(Constants.UNIVERSITY.getCode()+"M0000", item.getID(), item.getName(), item.getQuantity());
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}