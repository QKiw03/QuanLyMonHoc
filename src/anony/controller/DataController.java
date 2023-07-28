package anony.controller;

import java.util.List;
import anony.model.Subject;

/**
 *
 * @author braniumacademy <braniumacademy.net>
 */
public interface DataController {

    int SUBJECT = 1;
    int STUDENT = 2;
    int REGISTERING = 3;
    String SUBJECT_FILE = "SUBJECT.DAT";
    String STUDENT_FILE = "STUDENT.DAT";
    String REGISTERING_FILE = "STU_REGISTER.DAT";

    <T> void writeToFile(List<T> data, String fileName);

    <T> List<T> readDataFromFile(String fileName);
    
    void sortSubjectByNameASC(List<Subject> subjects);
    
    void sortSubjectByNameDESC(List<Subject> subjects);
    
    void sortSubjectByNumOfLessonASC(List<Subject> subjects);
    
    void sortSubjectByNumOfLessonDESC(List<Subject> subjects);
    
    List<Subject> searchSubjectByName(List<Subject> subjects, String key);
    
    List<Subject> searchSubjectByLesson(List<Subject> subjects, int from, int to);

}
