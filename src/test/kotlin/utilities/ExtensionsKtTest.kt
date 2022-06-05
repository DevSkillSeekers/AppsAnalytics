package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ExtensionsKtTest {

    @Test
    fun should_ReturnByteNumber_When_GiveValidStringSize(){
        //Given size
        val size = "1.5M"
        //when convert app size to byte
        val result = size.convertToByte()
        //then check the result
        val expectedResult = (1572864.0).toBigDecimal()
        assertEquals(expectedResult,result)
    }

    @Test
    fun should_ReturnByteNumber_When_GiveLowerCaseStringSize(){
        //Given size
        val size = "1.5m"
        //when convert app size to byte
        val result = size.convertToByte()
        //then check the result
        val expectedResult = (1572864.0).toBigDecimal()
        assertEquals(expectedResult,result)
    }

    @Test
    fun should_ReturnNull_When_SizeNotContainUnit(){
        //Given valid size without unit
        val size = "1.5"
        //when convert app size to byte
        val result = size.convertToByte()
        //then check the result
        assertNull(result)
    }
}