import java.io.*;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input/students2.csv");
        StudentRecordManager manager = new StudentRecordManager(file);
        String id = manager.loadDataForUser();
        ArrayList<StudentRecord> classes = manager.getCompletedClasses(id);
        manager.getStudentCreditForClasses(classes);
    }
}