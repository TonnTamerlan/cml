package com.cmlteam.geoip.utils;

/**
 * Class presents utility methods for IP like converting, checking, etc
 * 
 * @author Alexey Kopylov
 *
 */
public class IPv4 {
	
    private static final long BIGGEST_IP_ADDRESS = 4_294_967_295L;

    private static final int DIGITS_IN_BYTE = 8;
	
	private static final byte DIGIT_IN_BINARY_IP_ADDRESS = 32;
	
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
	 * Convert IP address from dot-decimal notation to decimal notation
	 * @param ip - string with ip address
	 * @return IP address in decimal notation
	 * @throws @{@link IllegalArgumentException} if string is not a IP address in dot-decimal notation
	 */
	public static long fromCanonicalDotDecimalToDecimal(String ip) {
		if(!isIPv4InDotDecimal(ip)) {
			String errorMessage = ip + " is not correct argument in convertFromCanonicalDotDecimalToDecimal method";
			throw new IllegalArgumentException(errorMessage);
		}
		String binaryIp = "";
		
		for (String octetDec : ip.split("\\.")) {
			String octetBin = Integer.toBinaryString(Integer.parseInt(octetDec));
			
			// if converted second and other octets is needed all "zero" digit which must be previously the first "one" digit
			if (!binaryIp.equals("")) {
				while (octetBin.length() < DIGITS_IN_BYTE) {
					octetBin = "0" + octetBin;
				}
			}
			binaryIp += octetBin;
		}
		return Long.parseLong(binaryIp, 2);
	}
	
	/**
	 * Convert IP address in dot-decimal notation (canonical and non-canonical) to decimal notation
	 * @param ip - string with ip address
     * @return IP address in decimal notation
     * @throws @{@link IllegalArgumentException} if string is not a IP address in dot-decimal notation or IP address is incorrect
     */
	public static long fromDotDecimalToDecimal(String ip) {
	    String likeIpAdressRegEx = "(\\d*\\.){0,3}\\d*";
        if(!ip.matches(likeIpAdressRegEx)) {
	        String errorMessage = ip + " is not correct ip address";
            throw new IllegalArgumentException(errorMessage);
	    }
	    String binaryIp = "";
	    
	    String[] octets = ip.split("\\.");
	    for (int i = 0; i < octets.length; i++) {
            String octetBin = Long.toBinaryString(Long.parseLong(octets[i]));
            
            
            // if converted second and other octets except last, is needed all "zero" digit which must be previously the first "one" digit
            if (i != 0 && i < octets.length - 1) {
                if(octetBin.length() > DIGITS_IN_BYTE) {
                    String errorMessage = ip + " is not correct ip address";
                    throw new IllegalArgumentException(errorMessage);
                }
                while (octetBin.length() < DIGITS_IN_BYTE) {
                    octetBin = "0" + octetBin;
                }
            }
            // fill last octet by "zero" digit
            if (i == octets.length - 1) {
                int octetsInCanonicalIpAddress = 4;
                int numberOfDigit = DIGITS_IN_BYTE + DIGITS_IN_BYTE * (octetsInCanonicalIpAddress - octets.length);
                if(octetBin.length() > numberOfDigit) {
                    String errorMessage = ip + " is not correct ip address";
                    throw new IllegalArgumentException(errorMessage);
                }
                while (octetBin.length() < numberOfDigit) {
                    octetBin = "0" + octetBin;
                }
            }
            binaryIp += octetBin;
            if(binaryIp.length() > DIGIT_IN_BINARY_IP_ADDRESS) {
                String errorMessage = ip + " is not correct ip address";
                throw new IllegalArgumentException(errorMessage);
            }
        }
	    
	    long ipAddresInDecimal = Long.parseLong(binaryIp, 2);
	    
	    if(ipAddresInDecimal > BIGGEST_IP_ADDRESS) {
	        String errorMessage = ip + " is not correct ip address";
            throw new IllegalArgumentException(errorMessage);
	    }
	    return ipAddresInDecimal;
	}
	
	public static String fromDecimalToDotDecimal(long ipDecimal) {
	    if(ipDecimal < 0 || ipDecimal > BIGGEST_IP_ADDRESS) {
	        String errorMessage = ipDecimal + " is not IPv4 address";
	        throw new IllegalArgumentException(errorMessage);
	    }
	    String ipBinary = Long.toBinaryString(ipDecimal);
	    
	    return binaryToDotDecimal(ipBinary);
	}

    private static String binaryToDotDecimal(String ipBinary) {
        while(ipBinary.length() < DIGIT_IN_BINARY_IP_ADDRESS) {
            ipBinary = "0" + ipBinary;
        }
        int firstOctet = Integer.parseInt(ipBinary.substring(0, 8), 2);
        int secondOctet = Integer.parseInt(ipBinary.substring(8, 16), 2);
        int thirdOctet = Integer.parseInt(ipBinary.substring(16, 24), 2);
        int fourthOctet = Integer.parseInt(ipBinary.substring(24, 32), 2);
               
        return firstOctet + "." + secondOctet + "." + thirdOctet + "." + fourthOctet;
    }

}
