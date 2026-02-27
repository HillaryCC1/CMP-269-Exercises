package org.example;

public class LehmanGradeBook {

    public boolean isPassing(int score) {
        if(score < 0 || score > 100) {
            throw new IllegalArgumentException();
        }
        return score >= 70;
    }

    public char getLetterGrade(int score) {
        if (score >= 90) return 'A';
        if (score >= 80) return 'B';
        if (score >= 70) return 'C';
        if (score >= 60) return 'D';
        return 'F';
    }

}
