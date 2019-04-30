package com.librarySystem.model;

import java.io.Serializable;

public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8299587761233943244L;
	private String ID;
	private String name;
	private int quantity;
	
	public Item(){
		
	}
	
	public Item (String ID, String name, int quantity){
		this.ID = ID;
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
