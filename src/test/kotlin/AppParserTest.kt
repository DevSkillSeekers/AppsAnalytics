import model.App
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import parser.DataParser
import utilities.Constant
import java.util.*
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AppParserTest{

    private lateinit var parser: DataParser

    @Test
    fun should_ReturnEmptyList_When_FileNotFound(){
        //Given filename that not found
        parser = DataParser("file")
        //when open the file to parse
        val result = parser.parseFile()
        //then check the result
        Assertions.assertEquals(mutableListOf<App>(),result)
    }

    @Test
    fun should_ReturnApp_when_ParsingSeparatedString () {
        // Given separated value string
        val separatedValue = "Books,Amazon,Libraries & Demo,April 20 2022,21M,500000,1.0.0,4.4"
        // When parsing string
        parser = DataParser("file")
        val excepted = App("Books", "Amazon", "Libraries & Demo", Date("4/20/2022"), "21M", 500000, "1.0.0", 4.4)
        val result = parser.addApp(separatedValue)
        // then
        assertEquals(excepted, result)
    }


    @Test
    fun should_ReturnListOfApp_When_CorrectFileName(){
        //Given filename
        parser = DataParser(Constant.FILE_NAME)
        //when open the file to parse
        val result = parser.parseFile()
        //then check the result
        Assertions.assertEquals(4426,result.size)
    }
}