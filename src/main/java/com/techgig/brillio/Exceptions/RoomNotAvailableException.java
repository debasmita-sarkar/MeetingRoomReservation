package com.techgig.brillio.Exceptions;

public class RoomNotAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomNotAvailableException(String msg) 
    {  
        super(msg); 
    }
}
