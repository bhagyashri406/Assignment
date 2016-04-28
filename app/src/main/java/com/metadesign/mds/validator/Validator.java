package com.metadesign.mds.validator;

import android.widget.EditText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class Validator {


	public static boolean isEmptyText(String text){
		if (text == null)
			return true;
		else if (text != null && text.length() == 0)
			return true;
		else if (text.equals("null") || text == "")
			return true;
		
			return false;
	}


	public static boolean isValidEmail(String emailStr) {
		boolean flag = false;
		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		if (emailStr == null)
			flag = false;
		
		else if (emailStr.matches(emailPattern) && emailStr.length() > 0)
			flag = true;
		
		return flag;
    }

}
