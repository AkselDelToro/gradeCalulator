import java.io.*;
import java.util.*;

public class StudentRecordManager {
    private static HashMap<String, ArrayList<StudentRecord>> _records = new HashMap<>(25);

    public StudentRecordManager(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file.getAbsolutePath()));
        int ct = 0;
        ArrayList<StudentRecord> record = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (ct == 0) {
                ct++;
                continue;
            }
            String[] toks = line.split(",");
            String sId = toks[0];
            String sClass = toks[1];
            String sGrade = toks[2];
            if (!_records.containsKey(sId)){
                StudentRecord temp = new StudentRecord(sId, sClass, sGrade);
                record.add(temp);
                _records.put(sId, record);
            } else {
                ArrayList<StudentRecord> temp;
                temp = _records.get(sId);
                temp.add(new StudentRecord(sId, sClass, sGrade));
                _records.put(sId, temp);
            }
        }
    }

    public String loadDataForUser() {
        String options = "";
        String cma = "";
        for (String k : _records.keySet()) {
            options += cma + k;
            cma = ", ";
        }
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter a valid Student ID [%s]: ", options);
            String theId = scan.next();
            if (_records.containsKey(theId)) {
                return theId;
            } else {
                System.out.printf("Error ID:%S is not found\n", theId);
            }
        }
    }
    public ArrayList<StudentRecord> getCompletedClasses(String studentID){
        ArrayList<StudentRecord> students = _records.get(studentID);
        ArrayList<StudentRecord> student = new ArrayList<>();
        System.out.print("\n -- The Student completed theses classes --");
        for (int i = 0; i < students.size(); i++) {
            if (Objects.equals(studentID, students.get(i).getSId())){
                System.out.printf("\nID:%s -- Class:%s -- Grade:%s -- Pts:%s", students.get(i).getSId(), students.get(i).getSClass(), students.get(i).getSGrade(), students.get(i).getGradePts());
                student.add(new StudentRecord(students.get(i).getSId(), students.get(i).getSClass(), students.get(i).getSGrade()));
            }
        } return student;
    }
    public void getStudentCreditForClasses(ArrayList<StudentRecord> records){
        HashMap<String, Double> classes = new HashMap<>();
        for (int i = 0; i < records.size(); i++) {
            String key = records.get(i).getSClass();
            Double value = records.get(i).getGradePts();
            if (!classes.containsKey(key)){
                classes.put(key, value);
            } else if (classes.get(key) < value){
                classes.replace(key,value);
            }
        }
        Double gpa = 0.0;
        int ct = 0;
        System.out.print("\n\n -- The Student Credit for these classes --");
        for (String key : classes.keySet()){
            System.out.printf("\nClass:%s --- GPA pts:%s", key, classes.get(key));
            gpa += classes.get(key);
            ct++;
        }
        gpa = Math.round((gpa/ct) * 10) / 10.0;
        System.out.printf("\n\nThe Overall GPA for the student is:%s\n", gpa);
    }
}