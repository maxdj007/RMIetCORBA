package com.librarySystem.corba;


/**
* corba/LibraryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./server.idl
* Thursday, 25 April, 2019 3:18:31 AM IST
*/

public interface LibraryOperations 
{
  boolean addItem (String managerID, String itemID, String itemName, int quantity);
  String findItem (String userID, String itemName);
  void shutdown ();
} // interface LibraryOperations
