package com.api.sendinblue.sender.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Utils {
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
			"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

	public static boolean isEmptyOrNull(String str) {
		return StringUtils.isAllBlank(str);
	}

	public static boolean isValidEmail(String email) {
		if(isEmptyOrNull(email)) {
			return false;
		}
		return EMAIL_PATTERN.matcher(email).matches();
	}
}
