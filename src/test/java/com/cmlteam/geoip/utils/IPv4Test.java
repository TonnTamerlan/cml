package com.cmlteam.geoip.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Testing IP util")
class IPv4Test {


	@Nested
	@DisplayName("Test isIPv4InDotDecimal method")
	class IsIPv4Test {

		@Test
		@DisplayName("Test with correct parameters")
		void testIsIPv4WithCorrectParameters() {
			assertTrue(IPv4.isIPv4InDotDecimal("12.12.12.12"));
			assertTrue(IPv4.isIPv4InDotDecimal("0.0.0.0"));
			assertTrue(IPv4.isIPv4InDotDecimal("255.255.255.255"));
		}
		
		@Test
		@DisplayName("Test with noncorrect parameters")
		void testIsIPv4WithNoncorrectParameters() {
			assertFalse(IPv4.isIPv4InDotDecimal("noncorrrect"));
			assertFalse(IPv4.isIPv4InDotDecimal("0.0.0.-1"));
			assertFalse(IPv4.isIPv4InDotDecimal("256.255.255.255"));
			assertFalse(IPv4.isIPv4InDotDecimal("255.255"));
			assertFalse(IPv4.isIPv4InDotDecimal("-255.255.255.0"));
			assertFalse(IPv4.isIPv4InDotDecimal("255.255.255,0"));
		}
	}

	@Nested
	@DisplayName("Test convertToDecimal method")
	class ConvertToDecimalTest{
		
		@Test
		@DisplayName("Test with correct parameters")
		void testConvertToDecimalWithCorrectParameters() {
			assertEquals(IPv4.convertToDecimal("0.0.0.0"), 0);
			assertEquals(IPv4.convertToDecimal("1.1.1.1"), 16843009);
			assertEquals(IPv4.convertToDecimal("12.12.255.255"), 202178559);
		}
		
		@Test
		@DisplayName("Test with noncorrect parameters")
		void testConvertToDecimalWithNonCorrectParameters() {
			String nonCorrectArgument = "noncorrect";
			Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
				IPv4.convertToDecimal(nonCorrectArgument);
			});
			assertEquals(nonCorrectArgument + " is not correct argument in convertToDecimal method", exception.getMessage());
		}
	}

}
