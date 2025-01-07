package jmu.ljy.service.impl;

import jmu.ljy.mapper.SystemMapper;
import jmu.ljy.pojo.Building;
import jmu.ljy.pojo.Dormitory;
import jmu.ljy.pojo.Records;
import jmu.ljy.pojo.Student;
import jmu.ljy.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SystemAdminServiceimpl implements SystemAdminService {

    @Autowired
    private SystemMapper systemMapper;

    @Override
    public void deleteRecord(int id) {
        systemMapper.deleteRecord(id);
    }

    // 根据类型查询信息记录
    @Override
    public List<Records> searchRecordsByMark(String mark) {
        return systemMapper.searchRecordsByMark(mark);
    }


    // 根据条件查询学生记录
    @Override
    public List<Student> searchStudents(Map<String, String> allParams) {
        return systemMapper.searchStudents(allParams);
    }

    @Override
    public void addStudent(String number, String name, String username,
                    String password, String gender, int dormitory_id){

//        System.out.println(dormitory_id);

        systemMapper.insertStudent(number, name, username,
                password, gender, dormitory_id);
        systemMapper.updateDormitoryAvailable(dormitory_id,-1);
    }


    @Override
    public void updateStudentMessage(int studentId,Integer dormitoryName,String updateName, String user_Name,String userPassword){
        int oldDormitoryId = systemMapper.searchDormitoryIdFromStudent(studentId);
        systemMapper.updateStudentMessage(studentId,dormitoryName,updateName,user_Name,userPassword);
        int newDormitoryId = systemMapper.searchDormitoryIdFromStudent(studentId);
        systemMapper.updateDormitoryAvailable(oldDormitoryId, 1);
        systemMapper.updateDormitoryAvailable(newDormitoryId, -1);

    }


    @Override
    @Transactional
    public void updateStudent(Student student) {
        // 先获取原宿舍ID和新宿舍ID
        Integer oldDormitoryId = student.getOldDormitory_Id();
        Integer newDormitoryId = student.getDormitory_Id();

        // 更新学生信息
        systemMapper.updateStudent(student);

        if (!(oldDormitoryId.equals(newDormitoryId))) {
            // 更新原宿舍床位数量
            if (oldDormitoryId != null) {
                systemMapper.updateDormitoryAvailable(oldDormitoryId, -1);
            }

            // 更新新宿舍床位数量
            if (newDormitoryId != null) {
                systemMapper.updateDormitoryAvailable(newDormitoryId, 1);
            }
        }
    }


    public void deleteStudent(int id){
        systemMapper.deleteStudent(id);
    }

    @Override
    public List<Dormitory> findAvailableDormitories() {
        return systemMapper.findAvailableDormitories();
    }

    // ------- 楼层管理相关 -------
    @Override
    public List<Building> getAllBuildings() {
        return systemMapper.getAllBuildings();
    }

    @Override
    public void addBuilding(String name, String introduction, int adminId) {
        systemMapper.addBuilding(name, introduction, adminId);
    }

    @Override
    public void updateBuilding(int id, String name, String introduction) {
        systemMapper.updateBuilding(id, name, introduction);
    }

    @Override
    public void deleteBuilding(int id) {
        systemMapper.deleteBuilding(id);
    }

    // ------- 宿舍管理相关 -------
    @Override
    public List<Dormitory> getDormitoriesByBuildingId(Integer buildingId) {
        return systemMapper.getDormitoriesByBuildingId(buildingId);
    }

    @Override
    public void addDormitory(int buildingId, String name, int type, int available, String telephone) {
        systemMapper.addDormitory(buildingId, name, type, available, telephone);
    }

    @Override
    public void updateDormitory(int id, String name, int type, int available, String telephone) {
        systemMapper.updateDormitory(id, name, type, available, telephone);
    }

    @Override
    public void deleteDormitory(int id) {
        systemMapper.deleteDormitory(id);
    }

    @Override
    public List<Building> searchBuildingsByName(String name) {
        return systemMapper.searchBuildingsByName(name);
    }

    @Override
    public boolean checkBuildingHasDormitories(int buildingId) {
        // 查询宿舍数量
        int dormitoryCount = systemMapper.countDormitoriesByBuildingId(buildingId);
        return dormitoryCount > 0; // 如果宿舍数大于0，则返回 true
    }

    @Override
    @Transactional
    public void deleteBuildingById(int buildingId) {
        // 删除与楼栋相关联的宿舍（避免外键约束问题）
        systemMapper.deleteDormitoriesByBuildingId(buildingId);

        // 删除楼栋
        systemMapper.deleteBuilding(buildingId);
    }
}