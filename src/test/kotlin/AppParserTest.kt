import model.App
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import parser.AppParser
import utilities.Constant

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AppParserTest{

    private lateinit var parser: AppParser

    @Test
    fun should_ReturnEmptyList_When_FileNotFound(){
        //Giving filename that not found
        parser = AppParser("file")
        //open the file to parse
        val result = parser.parseFile()
        Assertions.assertEquals(mutableListOf<App>(),result)
    }

    @Test
    fun should_ReturnListOfApp_When_CorrectFileName(){
        //Given filename
        parser = AppParser(Constant.FILE_NAME)
        //open the file to parse
        val result = parser.parseFile()
        Assertions.assertEquals(4426,result.size)
    }
}