package jmu.zzx.dormitory.pojo;



import java.time.LocalDateTime;


public class Student {
    private Integer id; // 学生的唯一标识符
    private String number; // 学生的学号
    private String name; // 学生的姓名
    private String username; // 学生的登录用户名
    private String password; // 学生的登录密码
    private String gender; // 学生的性别
    private Integer dormitory_Id; // 学生当前宿舍的ID
    private Integer oldDormitory_Id; // 学生之前的宿舍ID（如果有的话）
    private String dormitoryName; // 学生当前宿舍的名称
    private String state; // 学生的状态（例如：在校、休学等）
    private LocalDateTime create_Date; // 学生记录的创建日期和时间
    private LocalDateTime update_Date; // 学生记录的最后更新日期和时间

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(LocalDateTime update_Date) {
        this.update_Date = update_Date;
    }

    public LocalDateTime getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(LocalDateTime create_Date) {
        this.create_Date = create_Date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public Integer getOldDormitory_Id() {
        return oldDormitory_Id;
    }

    public void setOldDormitory_Id(Integer oldDormitory_Id) {
        this.oldDormitory_Id = oldDormitory_Id;
    }

    public Integer getDormitory_Id() {
        return dormitory_Id;
    }

    public void setDormitory_Id(Integer dormitory_Id) {
        this.dormitory_Id = dormitory_Id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
