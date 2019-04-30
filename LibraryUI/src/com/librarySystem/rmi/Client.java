package com.librarySystem.rmi;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;

public class Client {

	public LibraryInterface getLibrary(University university) {
		Registry registry;
		LibraryInterface obj = null;
		String registryURL = university.getCode();
		try {
			registry = LocateRegistry.getRegistry(Constants.PORT);
			for(int i = 0; i< registry.list().length; i++){
				System.out.println(registry.list()[i]);
			}
			registryURL = "rmi://"+Constants.HOSTANAME+":"+Constants.PORT+"/"+university.getCode()+"";
			obj = (LibraryInterface) Naming.lookup(registryURL);
		} catch (RemoteException | NotBoundException | MalformedURLException e) {
			System.out.println("The server is not running!!" + e.getMessage());
		}
		return obj;
	}

}
