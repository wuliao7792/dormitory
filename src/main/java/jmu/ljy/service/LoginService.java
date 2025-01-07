package jmu.ljy.service;

import jmu.ljy.pojo.DormitoryAdmin;
import jmu.ljy.pojo.Student;
import jmu.ljy.pojo.SystemAdmin;

public interface LoginService {

    public Student authenticateAsStudent(String username, String password) ;


    public DormitoryAdmin authenticateAsDormitoryAdmin(String username, String password);


    public SystemAdmin authenticateAsSystemAdmin(String username, String password) ;
}