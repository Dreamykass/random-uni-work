package sengine_tests.index_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import sengine.index.ExtractWords;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtractWordsTests {
    private static final Logger logger = LogManager.getLogger(ExtractWordsTests.class);

    @Test
    public void fromStringTest() {
        assertEquals(0, ExtractWords.fromString("").size());

        assertEquals(1, ExtractWords.fromString("xxx").size());
        assertEquals(1, ExtractWords.fromString(" xxx").size());
        assertEquals(1, ExtractWords.fromString("xxx").size());
        assertEquals(1, ExtractWords.fromString(" xxx ").size());

        assertEquals(2, ExtractWords.fromString("xxx xxx").size());
        assertEquals(2, ExtractWords.fromString("xxx         xxx").size());
        assertEquals(2, ExtractWords.fromString(" xxx xxx").size());
        assertEquals(2, ExtractWords.fromString("xxx xxx ").size());
        assertEquals(2, ExtractWords.fromString("xxx/xxx").size());
        assertEquals(2, ExtractWords.fromString("  xxx   +xxx").size());

        assertEquals(4, ExtractWords.fromString("assertEquals(0, ExtractWords.fromString(\"\").size());").size());
        assertEquals(6, ExtractWords.fromString("assertEquals(3, ExtractWords.fromString(\"  xxx   +xxx\").siź());").size());

//        assertEquals(6, ExtractWords.fromString("w chszczebrzeszynie chrząszcz brzmi w trzcinie").size());
//        assertEquals(9, ExtractWords.fromString("xąx xćx xęx xłx xńx xóx xśx xźx xżx").size());

    }
}
