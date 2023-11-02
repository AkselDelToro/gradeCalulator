import java.util.*;

public class StudentRecord {
    private String _sId;
    private String _sClass;
    private String _sGrade;
    private double _gradePts;

    public StudentRecord(String id, String sClass, String grade){
        _sId = id; _sClass = sClass; _sGrade = grade;
        if (Objects.equals(grade, "A")) _gradePts = 4.0;
        else if (Objects.equals(grade, "B")) _gradePts = 3.0;
        else if (Objects.equals(grade, "C")) _gradePts = 2.0;
        else if (Objects.equals(grade, "D")) _gradePts = 1.0;
        else if (Objects.equals(grade, "F")) _gradePts = 0.0;
    }

    public void setSId(String sId){_sId = sId;}
    public void setSClass(String sClass){_sClass = sClass;}
    public void setSGrade(String sGrade){_sGrade = sGrade;}
    public void setGradePts(double gradePts){_gradePts = gradePts;}

    public String getSId(){return  _sId;}
    public String getSClass(){return _sClass;}
    public String getSGrade(){return _sGrade;}
    public double getGradePts(){return _gradePts;}

    public String toString(){
        String s = "";
        System.out.printf("ID:%s --- Class:%s --- Grade:%s --- GradePts:%s", getSId(), getSClass(), getSGrade(), getGradePts());
        return s;
    }
}