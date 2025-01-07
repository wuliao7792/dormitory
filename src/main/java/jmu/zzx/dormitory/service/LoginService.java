package jmu.zzx.dormitory.service;

import jmu.zzx.dormitory.pojo.DormitoryAdmin;
import jmu.zzx.dormitory.pojo.Student;

public interface LoginService {

    public Student authenticateAsStudent(String username, String password) ;


    public DormitoryAdmin authenticateAsDormitoryAdmin(String username, String password);


}