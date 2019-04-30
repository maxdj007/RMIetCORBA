package com.librarySystem.constant;

public enum University {
	
	UNIVERSITY1("UN1", 9996),
	UNIVERSITY2("UN2", 9997);
	
	private final String code;
	private final int udpPort;
	
	private University(final String code, final int udpPort){
		this.code = code;
		this.udpPort = udpPort;
	}

	public String getCode() {
		return code;
	}
	
	public int getUdpPort(){
		return udpPort;
	}

}
