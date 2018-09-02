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
	@DisplayName("Test fromCanonicalDotDecimalToDecimal method")
	class FromCanonicalDotDecimalToDecimalTest{
		
		@Test
		@DisplayName("Test with correct parameters")
		void testFromCanonicalDotDecimalToDecimalWithCorrectParameters() {
			assertEquals(IPv4.fromCanonicalDotDecimalToDecimal("0.0.0.0"), 0);
			assertEquals(IPv4.fromCanonicalDotDecimalToDecimal("1.1.1.1"), 16843009);
			assertEquals(IPv4.fromCanonicalDotDecimalToDecimal("12.12.255.255"), 202178559);
		}
		
		@Test
		@DisplayName("Test with noncorrect parameters")
		void testFromCanonicalDotDecimalToDecimalWithNonCorrectParameters() {
			String nonCorrectArgument = "noncorrect";
			Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
				IPv4.fromCanonicalDotDecimalToDecimal(nonCorrectArgument);
			});
			assertEquals(nonCorrectArgument + " is not correct argument in convertFromCanonicalDotDecimalToDecimal method", exception.getMessage());
		}
	}
	
	@Nested
	@DisplayName("Test fromDotDecimalToDecimal method")
	class FromDotDecimalToDecimalTest {
	    
	    @Test
	    @DisplayName("Test with correct parameters")
	    void testFromDotDecimalToDecimalWithCorrectParameters() {
	        assertEquals(134_744_072, IPv4.fromDotDecimalToDecimal("8.8.8.8"));
	        assertEquals(4_294_967_295L, IPv4.fromDotDecimalToDecimal("255.255.255.255"));
	        assertEquals(4_294_902_015L, IPv4.fromDotDecimalToDecimal("255.255.255"));
	        assertEquals(134_218_984, IPv4.fromDotDecimalToDecimal("8.1256"));
	        assertEquals(568_579, IPv4.fromDotDecimalToDecimal("568579"));
	    }
	    
	    @Test
	    @DisplayName("Test with noncorrect parameters")
	    void testFromDotDecimalToDecimalWithNonCorrectParameters(){
	        String withAlfabet = "noncorrect";
	        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	           IPv4.fromDotDecimalToDecimal(withAlfabet); 
	        });
	        assertEquals(withAlfabet + " is not correct ip address", exception.getMessage());
	        
	        String fiveOctets = "125365.12222.12210.21233.12122";
            exception = assertThrows(IllegalArgumentException.class, () -> {
               IPv4.fromDotDecimalToDecimal(fiveOctets); 
            });
            assertEquals(fiveOctets + " is not correct ip address", exception.getMessage());
            
            String oneCharacter = "1253.t255.566.6215";
            exception = assertThrows(IllegalArgumentException.class, () -> {
               IPv4.fromDotDecimalToDecimal(oneCharacter); 
            });
            assertEquals(oneCharacter + " is not correct ip address", exception.getMessage());
            
            String wrongIP_1 = "256.255.255.255";
            exception = assertThrows(IllegalArgumentException.class, () -> {
               IPv4.fromDotDecimalToDecimal(wrongIP_1); 
            });
            assertEquals(wrongIP_1 + " is not correct ip address", exception.getMessage());
         
            String wrongIP_2 = "12.242332432";
            exception = assertThrows(IllegalArgumentException.class, () -> {
                IPv4.fromDotDecimalToDecimal(wrongIP_2);
            });
            assertEquals(wrongIP_2 + " is not correct ip address", exception.getMessage());
	    }
	    
	}
	
	@Nested
	@DisplayName("Test fromDecimalToDotDecimal method")
	class FromDecimalToDotDecimalTest {
	    
	    private static final long BIGGEST_IP_ADDRESS = 4_294_967_295L;

        @Test
	    @DisplayName("Test with correct parameters")
	    void testFromDecimalToDotDecimalWithCorrectParameters() {
	        assertEquals("0.0.0.0", IPv4.fromDecimalToDotDecimal(0));
	        assertEquals("125.125.125.125", IPv4.fromDecimalToDotDecimal(2_105_376_125L));
	        assertEquals("255.255.255.255", IPv4.fromDecimalToDotDecimal(BIGGEST_IP_ADDRESS));
	    }
	    
	    @Test
        @DisplayName("Test with noncorrect parameters")
        void testFromDecimalToDotDecimalWithNonCorrectParameters() {
	        long lessZero = -1;
	        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
	           IPv4.fromDecimalToDotDecimal(lessZero); 
	        });
	        assertEquals(lessZero + " is not IPv4 address", exception.getMessage());
	        
	        long largerBiggestIpAddress = BIGGEST_IP_ADDRESS + 1;
	        exception = assertThrows(IllegalArgumentException.class, () -> {
	           IPv4.fromDecimalToDotDecimal(largerBiggestIpAddress); 
	        });
	        assertEquals(largerBiggestIpAddress + " is not IPv4 address", exception.getMessage());
	    }
	}

}
