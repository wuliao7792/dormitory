package jmu.ljy.mapper;

import jmu.ljy.pojo.Building;
import jmu.ljy.pojo.Dormitory;
import jmu.ljy.pojo.Records;
import jmu.ljy.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SystemMapper {

    // 删除信息记录
    @Delete("DELETE FROM information_records WHERE id = #{id}")
    int deleteRecord(@Param("id") int id);

    // 根据类型查询信息记录
    @Select("<script>" +
            "SELECT * FROM information_records" +
            "<if test='mark != null and mark != \"\"'>" +
            " WHERE mark = #{mark}" +
            "</if>" +
            "</script>")
    List<Records> searchRecordsByMark(@Param("mark") String mark);



    // 根据条件查询学生记录
    @Select("<script>" +
            "SELECT * FROM student" +
            "<where>" +
            "<if test='allParams.number != null and allParams.number != \"\"'>" +
            " AND number = #{allParams.number}" +
            "</if>" +
            "<if test='allParams.name != null and allParams.name != \"\"'>" +
            " AND name = #{allParams.name}" +
            "</if>" +
            "<if test='allParams.gender != null and allParams.gender != \"\"'>" +
            " AND gender = #{allParams.gender}" +
            "</if>" +
            "<if test='allParams.dormitoryId != null and allParams.dormitoryId != \"\"'>" +
            " AND dormitory_id = #{allParams.dormitoryId}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Student> searchStudents(@Param("allParams") Map<String, String> allParams);




    @Select("SELECT dormitory_id from student where id = #{id}")
    int searchDormitoryIdFromStudent(@Param("id") int id);


    @Update({
            "<script>",
            "UPDATE student",
            "<set>",
            "<if test='dormitory_id != null and dormitory_id != 0'>",
            "dormitory_id = #{dormitory_id},",
            "</if>",
            "<if test='updateName != null and updateName != \"\"'>",
            "name = #{updateName},",
            "</if>",
            "<if test='user_Name != null and user_Name != \"\"'>",
            "username = #{user_Name},",
            "</if>",
            "<if test='userPassword != null and userPassword != \"\"'>",
            "password = #{userPassword},",
            "</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    void updateStudentMessage(@Param("id") int id, @Param("dormitory_id") int dormitory_id,
                              @Param("updateName") String updateName, @Param("user_Name") String user_Name,
                              @Param("userPassword") String userPassword);


    @Insert("INSERT INTO student(number,name,username,password,gender,dormitory_id) VALUES " +
            "(#{number},#{name},#{username},#{password},#{gender},#{dormitory_id})")
    void insertStudent(@Param("number") String number, @Param("name") String name, @Param("username") String username,
                       @Param("password") String password, @Param("gender") String gender, @Param("dormitory_id") int dormitory_id);


    @Delete("DELETE FROM student where id = #{id}")
    void deleteStudent(@Param("id") int id);



    @Update("UPDATE dormitory SET available = available + #{change} WHERE id = #{dormitoryId}")
    int updateDormitoryAvailable(@Param("dormitoryId") int dormitoryId, @Param("change") int change);



    @Update("UPDATE student SET number=#{number}, name=#{name}, username=#{username}, password=#{password}, gender=#{gender}, dormitory_id=#{dormitory_Id}, state=#{state} WHERE id=#{id}")
    int updateStudent(Student student);


    @Select("SELECT id, name FROM dormitory WHERE available > 0")
    List<Dormitory> findAvailableDormitories();

    // 楼层管理
    @Select("SELECT * FROM building")
    List<Building> getAllBuildings();

    @Insert("INSERT INTO building (name, introduction, admin_id) VALUES (#{name}, #{introduction}, #{adminId})")
    void addBuilding(@Param("name") String name, @Param("introduction") String introduction, @Param("adminId") int adminId);

    @Update("UPDATE building SET name = #{name}, introduction = #{introduction} WHERE id = #{id}")
    void updateBuilding(@Param("id") int id, @Param("name") String name, @Param("introduction") String introduction);

    @Delete("DELETE FROM building WHERE id = #{id}")
    void deleteBuilding(@Param("id") int id);

    // 宿舍管理
    @Select("SELECT * FROM dormitory WHERE building_id = #{buildingId} OR #{buildingId} IS NULL")
    List<Dormitory> getDormitoriesByBuildingId(@Param("buildingId") Integer buildingId);

    @Insert("INSERT INTO dormitory (building_id, name, type, available, telephone) VALUES (#{buildingId}, #{name}, #{type}, #{available}, #{telephone})")
    void addDormitory(@Param("buildingId") int buildingId, @Param("name") String name, @Param("type") int type, @Param("available") int available, @Param("telephone") String telephone);

    @Update("UPDATE dormitory SET name = #{name}, type = #{type}, available = #{available}, telephone = #{telephone} WHERE id = #{id}")
    void updateDormitory(@Param("id") int id, @Param("name") String name, @Param("type") int type, @Param("available") int available, @Param("telephone") String telephone);

    @Delete("DELETE FROM dormitory WHERE id = #{id}")
    void deleteDormitory(@Param("id") int id);

    @Select("<script>" +
            " SELECT * FROM building " +
            " WHERE 1=1 " +
            " <if test='name != null and name.trim() != \"\"'> " +
            "   AND name LIKE CONCAT('%', #{name}, '%') " +
            " </if> " +
            "</script>")
    List<Building> searchBuildingsByName(@Param("name") String name);

    // 查询楼栋关联的宿舍数量
    @Select("SELECT COUNT(*) FROM dormitory WHERE building_id = #{buildingId}")
    int countDormitoriesByBuildingId(@Param("buildingId") int buildingId);

    // 删除宿舍相关方法
    @Delete("DELETE FROM dormitory WHERE building_id = #{buildingId}")
    void deleteDormitoriesByBuildingId(@Param("buildingId") int buildingId);

    @Select("<script>" +
            "SELECT * FROM dormitory " +
            "<where>" +
            "<if test='buildingId != null'>" +
            "   AND building_id = #{buildingId}" +
            "</if>" +
            "<if test='name != null and name != \"\"'>" +
            "   AND name LIKE CONCAT('%', #{name}, '%')" +
            "</if>" +
            "<if test='type != null'>" +
            "   AND type = #{type}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Dormitory> searchDormitories(@Param("buildingId") Integer buildingId, @Param("name") String name, @Param("type") Integer type);

    // 查询宿舍关联的学生数量
    @Select("SELECT COUNT(*) FROM student WHERE dormitory_id = #{dormitoryId}")
    int countStudentsByDormitoryId(@Param("dormitoryId") int dormitoryId);

    // 查询宿舍可用床位数
    @Select("SELECT available FROM dormitory WHERE id = #{dormitoryId}")
    int getDormitoryAvailable(@Param("dormitoryId") int dormitoryId);
}
