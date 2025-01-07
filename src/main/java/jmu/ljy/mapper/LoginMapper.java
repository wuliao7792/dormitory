package jmu.ljy.mapper;

import jmu.ljy.pojo.DormitoryAdmin;
import jmu.ljy.pojo.Student;
import jmu.ljy.pojo.SystemAdmin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginMapper {

    @Select("SELECT * FROM student WHERE username = #{username} AND password = #{password}")
    Student getStudentByCredentials(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM dormitory_admin WHERE username = #{username} AND password = #{password}")
    DormitoryAdmin getDormitoryAdminByCredentials(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM system_admin WHERE username = #{username} AND password = #{password}")
    SystemAdmin getSystemAdminByCredentials(@Param("username") String username, @Param("password") String password);
}
