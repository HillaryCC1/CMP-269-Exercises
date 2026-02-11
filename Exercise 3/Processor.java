import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Processor {

    
    private static final int LOW_GRADE_CUTOFF = 60;

    public static void main(String[] args) {
        System.out.println("Starting processing...");

        try (
                Scanner fileScanner = new Scanner(new File("students.txt"));
                PrintWriter out = new PrintWriter("grades_report.txt")
        ) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                try (Scanner lineScanner = new Scanner(line)) {
                    String name = lineScanner.next();

                    if (!lineScanner.hasNextInt()) throw new NumberFormatException("Missing/invalid s1");
                    int s1 = lineScanner.nextInt();

                    if (!lineScanner.hasNextInt()) throw new NumberFormatException("Missing/invalid s2");
                    int s2 = lineScanner.nextInt();

                    if (!lineScanner.hasNextInt()) throw new NumberFormatException("Missing/invalid s3");
                    int s3 = lineScanner.nextInt();

                    if (lineScanner.hasNext()) throw new NumberFormatException("Extra token(s)");


                    validateScore(s1);
                    validateScore(s2);
                    validateScore(s3);


                    checkForLowGrade(name, s1, s2, s3);

                    double avg = (s1 + s2 + s3) / 3.0;
                    out.printf("Student: %s | Scores: %d %d %d | Average: %.2f%n", name, s1, s2, s3, avg);

                } catch (LowGradeException e) {

                    System.out.println("Low grade: " + e.getMessage());
                    out.printf("Student: %s | ***LOW GRADE ALERT*** (%s)%n", extractName(line), e.getMessage());

                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid number found. Skipping line -> " + line);

                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: Score out of range. Skipping line -> " + line);

                } catch (Exception e) {
                    System.out.println("Warning: Corrupted line. Skipping -> " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: students.txt not found. Check the file name/location.");
        } finally {
            System.out.println("Processing Complete");
        }
    }

    private static void validateScore(int s) {
        if (s < 0 || s > 100) {
            throw new IllegalArgumentException("Score must be 0..100, got: " + s);
        }
    }

    private static void checkForLowGrade(String name, int s1, int s2, int s3) throws LowGradeException {
        if (s1 < LOW_GRADE_CUTOFF || s2 < LOW_GRADE_CUTOFF || s3 < LOW_GRADE_CUTOFF) {
            throw new LowGradeException(
                    String.format("%s has a score below %d (scores: %d, %d, %d)",
                            name, LOW_GRADE_CUTOFF, s1, s2, s3)
            );
        }
    }


    private static String extractName(String line) {
        try (Scanner s = new Scanner(line)) {
            return s.hasNext() ? s.next() : "Unknown";
        }
    }
}