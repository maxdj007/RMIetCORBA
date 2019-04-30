package com.librarySystem.rmi;

import java.rmi.Naming;

import com.librarySystem.constant.Constants;
import com.librarySystem.utility.Utilities;

public class Server {

	public LibraryInterface URLRegistry() {
		try {
			// Call initRegistry to check whether the server is already
			// registered on the port or a new registry is to be made.
			Utilities.initRegistry(Constants.RMI_PORT);

			LibraryInterface LibImpl = new LibraryImpl();
			String registryURL = "rmi://"+Constants.HOSTANAME+":"+Constants.RMI_PORT+"/"+Constants.UNIVERSITY.getCode()+"";
			System.out.println("Registry URL - "+ registryURL);
			Naming.rebind(registryURL, LibImpl);
			System.out.println("Server is Running...");

			return LibImpl;
		} catch (Exception e) {
			System.out.println("The server did not start!! "+e.getMessage());
		}

		return null;
	}

}
