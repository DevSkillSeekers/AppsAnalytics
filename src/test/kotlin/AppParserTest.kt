import model.App
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import parser.CSVParser
import utilities.Constant

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AppParserTest{

    private lateinit var parser: CSVParser

    @Test
    fun should_ReturnEmptyList_When_FileNotFound(){
        //Given filename that not found
        parser = CSVParser("file")
        //when open the file to parse
        val result = parser.getAllApps()
        //then check the result
        Assertions.assertEquals(mutableListOf<App>(),result)
    }

    @Test
    fun should_ReturnListOfApp_When_CorrectFileName(){
        //Given filename
        parser = CSVParser(Constant.FILE_NAME)
        //when open the file to parse
        val result = parser.getAllApps()
        //then check the result
        Assertions.assertEquals(4426,result.size)
    }
}