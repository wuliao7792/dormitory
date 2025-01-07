package jmu.ljy.service;

import jmu.ljy.dto.DormitoryInfoDTO;
import jmu.ljy.dto.DormitoryStudentDTO;
import jmu.ljy.pojo.Records;

import java.util.List;

public interface DormitoryAdminService {
    public List<DormitoryInfoDTO> getDormitoryInfoByAdminId(int adminId, String name, boolean available);


    public List<DormitoryStudentDTO> getStudentInfoByAdminId(int adminId, String roomName);


    public List<Records> findRecordsByIdAndMark(int adminId,String mark);


    public void updateRepairRecord(int id, String audit);

    public void updateMoveoutRecord(int id, String audit, String targetDormitoryName);

    public void saveRecord(String title, String content,
                           String mark, Integer dormitoryId, int adminId,
                           String buildingName, String dormitoryName,
                           String name);


    public void updateNoticeRecord(int id,String title, String content);

    public void deleteRecord(int id);

    public List<String> getBuildingNames(int id);
}
