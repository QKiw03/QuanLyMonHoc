package anony.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import anony.controller.sort.SortSubjectByNameASC;
import anony.controller.sort.SortSubjectByNameDESC;
import anony.controller.sort.SortSubjectByNumOfLessonASC;
import anony.controller.sort.SortSubjectByNumOfLessonDESC;
import anony.model.Subject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author braniumacademy <braniumacademy.net>
 */
public class DataControllerImp implements DataController {

    @Override
    public <T> void writeToFile(List<T> data, String fileName) {
        try ( FileOutputStream fos = new FileOutputStream(fileName);  
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public <T> List<T> readDataFromFile(String fileName) {
        List<T> data = new ArrayList<>();
        File file = new File(fileName);
        if (file.length() > 0) {
            try ( FileInputStream fis = new FileInputStream(file);  
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                data = (List<T>) ois.readObject();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public void sortSubjectByNameASC(List<Subject> subjects) {
        Collections.sort(subjects, new SortSubjectByNameASC());
    }

    @Override
    public void sortSubjectByNameDESC(List<Subject> subjects) {
        Collections.sort(subjects, new SortSubjectByNameDESC());
    }

    @Override
    public void sortSubjectByNumOfLessonASC(List<Subject> subjects) {
        Collections.sort(subjects, new SortSubjectByNumOfLessonASC());
    }

    @Override
    public void sortSubjectByNumOfLessonDESC(List<Subject> subjects) {
        Collections.sort(subjects, new SortSubjectByNumOfLessonDESC());
    }

    @Override
    public List<Subject> searchSubjectByName(List<Subject> subjects, String key) {
           List<Subject> resultList = new ArrayList<>();
           var regex = ".*" + key + ".*";  // bất kì chỗ nào có chứa key
           Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
           Matcher matcher;
           for (Subject subject : subjects) {
               matcher = pattern.matcher(subject.getName());
               if(matcher.matches()){
                   resultList.add(subject);
               }
           }
           return resultList;
    }

    @Override
    public List<Subject> searchSubjectByLesson(List<Subject> subjects, int from, int to) {
        List<Subject> resultList = new ArrayList<>();
           for (Subject subject : subjects) {
               if(subject.getNumOfLesson() >= from 
                       && subject.getNumOfLesson()<= to){
                   resultList.add(subject);
               }
           }
           return resultList;
    }

}
