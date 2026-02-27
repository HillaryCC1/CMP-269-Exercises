package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testPassingGrade() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertTrue(gb.isPassing(70));
        assertFalse(gb.isPassing(69));
    }

    @Test
    void testLetterGrades() {
        LehmanGradeBook gb = new LehmanGradeBook();

        assertEquals('A', gb.getLetterGrade(90));
        assertEquals('B', gb.getLetterGrade(80));
        assertEquals('C', gb.getLetterGrade(70));
        assertEquals('D', gb.getLetterGrade(60));
        assertEquals('F', gb.getLetterGrade(59));
    }
}
