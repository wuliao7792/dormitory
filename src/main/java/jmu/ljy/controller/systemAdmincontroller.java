package jmu.ljy.controller;

import jmu.ljy.pojo.Building;
import jmu.ljy.pojo.Dormitory;
import jmu.ljy.pojo.Records;
import jmu.ljy.pojo.Student;
import jmu.ljy.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;

@Controller
public class systemAdmincontroller {

    @Autowired
    private SystemAdminService systemAdminService;

    @GetMapping("/systemAdmin")
    public String system(Model model) {
        // 省略其他代码
        return "systemAdmin";
    }

    // 查询学生信息
    @GetMapping("/systemAdmin/searchStudents")
    public String searchStudents(@RequestParam Map<String, String> allParams, Model model) {
        List<Student> students = systemAdminService.searchStudents(allParams);
        model.addAttribute("students", students);
        return "systemAdmin";
    }

    @PostMapping("/systemAdmin/addStudent")
    public String addStudent(@RequestParam String number, @RequestParam String name, @RequestParam String username,
                             @RequestParam String password, @RequestParam String gender, @RequestParam int dormitory_id) {
        systemAdminService.addStudent(number,name,username,password,gender,dormitory_id);
        // 返回重定向到学生管理页面或视图
        return "redirect:/systemAdmin";
    }


    @PostMapping("/systemAdmin/updateStudent")
    public String updateStudent(@RequestParam(value = "dormitoryName",required = false) Integer dormitoryName,
                                @RequestParam("studentId") int studentId,
                                @RequestParam(value = "updateName", required = false) String updateName,
                                @RequestParam(value = "user_Name", required = false) String user_Name,
                                @RequestParam(value = "userPassword", required = false) String userPassword) {
        systemAdminService.updateStudentMessage(studentId,dormitoryName,updateName,user_Name,userPassword);
        return "redirect:/systemAdmin";
    }

    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id) {
        systemAdminService.deleteStudent(id);
        return "redirect:/systemAdmin";
    }


    @GetMapping("/getAvailableDormitories")
    public ResponseEntity<List<Dormitory>> getAvailableDormitories() {
        List<Dormitory> dormitories = systemAdminService.findAvailableDormitories();
        return ResponseEntity.ok(dormitories);
    }

    // 查询信息记录
    @GetMapping("/records")
    public String searchRecords(@RequestParam(value = "mark", required = false) String mark, Model model) {
        List<Records> records = systemAdminService.searchRecordsByMark(mark);
        model.addAttribute("records", records);
        return "systemAdmin";
    }

    // 删除信息记录
    @PostMapping("/deleteRecord/{id}")
    public String deleteRecord(@PathVariable int id) {
        systemAdminService.deleteRecord(id);
        return "redirect:/systemAdmin";
    }

    // ------- 楼层管理相关 -------
    @GetMapping("/systemAdmin/buildings")
    public String listBuildings(Model model) {
        List<Building> buildings = systemAdminService.getAllBuildings();
        model.addAttribute("buildings", buildings); // 将楼栋数据传递到前端
        return "systemAdmin"; // 指定返回的页面
    }

    @PostMapping("/systemAdmin/addBuilding")
    public String addBuilding(@RequestParam String name, @RequestParam String introduction, @RequestParam int adminId) {
        systemAdminService.addBuilding(name, introduction, adminId);
        return "redirect:/systemAdmin/buildings";
    }

    @PostMapping("/systemAdmin/updateBuilding")
    public String updateBuilding(@RequestParam int id, @RequestParam String name, @RequestParam String introduction) {
        systemAdminService.updateBuilding(id, name, introduction);
        return "redirect:/systemAdmin/buildings";
    }



    // ------- 宿舍管理相关 ------
    @GetMapping("/systemAdmin/dormitories")
    public String listDormitories(@RequestParam(value = "buildingId", required = false) Integer buildingId, Model model) {
        List<Dormitory> dormitories = systemAdminService.getDormitoriesByBuildingId(buildingId);
        model.addAttribute("dormitories", dormitories); // 将宿舍数据传递到前端
        return "systemAdmin";
    }

    @PostMapping("/systemAdmin/addDormitory")
    public String addDormitory(@RequestParam int buildingId,
                               @RequestParam String name,
                               @RequestParam int type,
                               @RequestParam int available,
                               @RequestParam String telephone) {
        systemAdminService.addDormitory(buildingId, name, type, available, telephone);
        return "redirect:/systemAdmin/dormitories";
    }

    @PostMapping("/systemAdmin/updateDormitory")
    public String updateDormitory(@RequestParam int id,
                                  @RequestParam String name,
                                  @RequestParam int type,
                                  @RequestParam int available,
                                  @RequestParam String telephone) {
        systemAdminService.updateDormitory(id, name, type, available, telephone);
        return "redirect:/systemAdmin/dormitories";
    }

    @PostMapping("/systemAdmin/deleteDormitory/{id}")
    public String deleteDormitory(@PathVariable int id) {
        try {
            systemAdminService.deleteDormitory(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/systemAdmin/dormitories?error=deleteDormitory";
        }
        return "redirect:/systemAdmin/dormitories";
    }

    // 查询楼层信息
    @GetMapping("/systemAdmin/searchBuildings")
    public String searchBuildings(@RequestParam(value = "name", required = false) String name, Model model) {
        // 调用服务获取查询结果
        List<Building> buildings = systemAdminService.searchBuildingsByName(name);
        model.addAttribute("buildings", buildings); // 查询结果传递到前端
        return "systemAdmin"; // 返回楼层管理页面
    }

    // 检查楼层是否有关联宿舍
    @GetMapping("/systemAdmin/checkBuilding/{id}")
    public ResponseEntity<Map<String, Object>> checkBuilding(@PathVariable int id) {
        // 调用服务层检查是否有关联宿舍
        boolean hasDormitories = systemAdminService.checkBuildingHasDormitories(id);

        // 构造返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("hasDormitories", hasDormitories);

        // 返回JSON格式数据
        return ResponseEntity.ok(result);
    }

    // 删除楼层接口（保留原有功能）
    @PostMapping("/systemAdmin/deleteBuilding/{id}")
    public String deleteBuilding(@PathVariable int id) {
        try {
            systemAdminService.deleteBuildingById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/systemAdmin/buildings?error=deleteBuilding";
        }
        return "redirect:/systemAdmin/buildings"; // 成功返回楼层管理页面
    }

    // Controller层增加查询方法
    @GetMapping("/systemAdmin/searchDormitories")
    public String searchDormitories(@RequestParam(value = "buildingId", required = false) Integer buildingId,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "type", required = false) Integer type,
                                    Model model) {
        List<Dormitory> dormitories = systemAdminService.searchDormitories(buildingId, name, type);
        model.addAttribute("dormitories", dormitories);
        return "systemAdmin";
    }

    @GetMapping("/systemAdmin/checkDormitory/{id}")
    public ResponseEntity<Map<String, Object>> checkDormitory(@PathVariable int id) {
        // 调用服务层检查是否有关联学生
        boolean hasStudents = systemAdminService.checkDormitoryHasStudents(id);

        // 构造返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("hasStudents", hasStudents);

        // 返回JSON格式数据
        return ResponseEntity.ok(result);
    }

    @GetMapping("/systemAdmin/checkDormitoryAvailable/{id}")
    public ResponseEntity<Map<String, Object>> checkDormitoryAvailable(@PathVariable int id) {
// 调用服务层检查宿舍可用床位数
        int available = systemAdminService.checkDormitoryAvailable(id);

// 构造返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("available", available);

// 返回JSON格式数据
        return ResponseEntity.ok(result);
    }
}

