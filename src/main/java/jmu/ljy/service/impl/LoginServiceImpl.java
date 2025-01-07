package jmu.ljy.service.impl;

import jmu.ljy.mapper.LoginMapper;
import jmu.ljy.pojo.DormitoryAdmin;
import jmu.ljy.pojo.Student;
import jmu.ljy.pojo.SystemAdmin;
import jmu.ljy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 标记这个类为Spring的服务组件
// 实现LoginService接口
public class LoginServiceImpl implements LoginService {

    @Autowired
    // 自动注入LoginMapper
    private LoginMapper loginMapper;

    @Override
    // 调取学生身份的方法
    public Student authenticateAsStudent(String username, String password) {
        return loginMapper.getStudentByCredentials(username, password);
    }

    @Override
    // 调取宿舍管理员身份的方法
    public DormitoryAdmin authenticateAsDormitoryAdmin(String username, String password) {
        return loginMapper.getDormitoryAdminByCredentials(username, password);
    }

    @Override
    // 调取系统管理员身份的方法
    public SystemAdmin authenticateAsSystemAdmin(String username, String password) {
        return loginMapper.getSystemAdminByCredentials(username, password); // 调用Mapper的方法
    }
}
