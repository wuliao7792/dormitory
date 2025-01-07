package jmu.zzx.dormitory.pojo;

import lombok.Data;

@Data
public class Dormitory {
    private Integer id; // 宿舍的唯一标识符
    private Integer building_id; // 宿舍所属楼宇的ID
    private String buildingName; // 宿舍所属楼宇的名称
    private String name; // 宿舍的名称
    private Integer type; // 宿舍的类型（例如：单人间、双人间等）
    private Integer available; // 宿舍的可用床位数
    private String telephone; // 宿舍相关的联系电话

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
