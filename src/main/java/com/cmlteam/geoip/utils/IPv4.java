package com.cmlteam.geoip.utils;

/**
 * Class presents utility methods for IP like converting, checking, etc
 * 
 * @author Alexey Kopylov
 *
 */
public class IPv4 {
	
	private static final String IPv4_REG_EXP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" + 
			"(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	
	private IPv4() {}
	
	/**
	 * Check if string is IPv4 address in dot-decimal notation
	 * 
	 * @param ip - string with IP address
	 * @return true - if the string is IP address in dot-decimal notation, otherwise return false
	 */
	public static boolean isIPv4InDotDecimal(String ip) {
		if(ip == null) {
		    return false;
		}
	    return ip.matches(IPv4_REG_EXP);
	}
	
	/**
	 * Convert IP address from dot-deciaml notation to decimal notation
	 * @param ip - string with ip address
	 * @return IP address in decimal notation
	 * @throws @{@link IllegalArgumentException} if string with ip is not a IP address in dot-decimal notation
	 */
	public static long convertToDecimal(String ip) {
		if(!isIPv4InDotDecimal(ip)) {
			String errorMessage = ip + " is not correct argument in convertToDecimal method";
			throw new IllegalArgumentException(errorMessage);
		}
		String binaryIp = "";
		
		for (String octetDec : ip.split("\\.")) {
			String octetBin = Integer.toBinaryString(Integer.parseInt(octetDec));
			
			// if converted second and other octets is needed all "zero" digit which must be previously the first "one" digit
			if (!binaryIp.equals("")) {
				while (octetBin.length() < 8) {
					octetBin = "0" + octetBin;
				}
			}
			binaryIp += octetBin;
		}
		return Long.parseLong(binaryIp, 2);
	}
	

}
