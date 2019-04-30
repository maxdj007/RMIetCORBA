package com.librarySystem.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.librarySystem.constant.Constants;
import com.librarySystem.constant.University;
import com.librarySystem.model.Item;

public class Utilities {
	
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

	public static University getUniversity(String userId) {
		if (University.UNIVERSITY1.getCode().equals(userId.substring(0, 3))) {
			return University.UNIVERSITY1;
		} else {
			return University.UNIVERSITY2;
		}
	}
	
	public static boolean matchesOrNot(String regex, String string){
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(string);
		if(matcher.find()) {
			return true;
		}
		return false;
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
	
}
