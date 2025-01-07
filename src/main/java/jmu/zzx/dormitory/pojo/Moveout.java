package jmu.zzx.dormitory.pojo;

import lombok.Data;

@Data
public class Moveout {
    private Integer id; // 记录的唯一标识符
    private Integer studentId; // 学生的ID
    private String studentName; // 学生的姓名
    private Integer dormitoryId; // 宿舍的ID
    private String dormitoryName; // 宿舍的名称
    private String reason; // 申请或变更宿舍的原因
    private String createDate; // 记录的创建日期
}
