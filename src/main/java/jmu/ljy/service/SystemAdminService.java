package jmu.ljy.service;

import jmu.ljy.pojo.Dormitory;
import jmu.ljy.pojo.Records;
import jmu.ljy.pojo.Student;
import jmu.ljy.pojo.Building;

import java.util.List;
import java.util.Map;

public interface SystemAdminService {

    public void deleteRecord(int id);

    public List<Records> searchRecordsByMark(String mark);


    public List<Student> searchStudents(Map<String, String> allParams);

    public void updateStudent(Student student);

    public List<Dormitory> findAvailableDormitories();

    public void updateStudentMessage(int studentId,Integer dormitoryName,String updateName,String user_Name,String userPassword);

    public void deleteStudent(int id);

    public void addStudent(String number, String name, String username,
                           String password, String gender, int dormitory_id);

    // 楼层管理
    List<Building> getAllBuildings();

    void addBuilding(String name, String introduction, int adminId);

    void updateBuilding(int id, String name, String introduction);

    void deleteBuilding(int id);

    // 宿舍管理
    List<Dormitory> getDormitoriesByBuildingId(Integer buildingId);

    void addDormitory(int buildingId, String name, int type, int available, String telephone);

    void updateDormitory(int id, String name, int type, int available, String telephone);

    void deleteDormitory(int id);

    List<Building> searchBuildingsByName(String name);

    // 校验是否存在关联宿舍
    boolean checkBuildingHasDormitories(int buildingId);

    // 删除楼栋
    void deleteBuildingById(int buildingId); // 修改名称以防冲突
}

