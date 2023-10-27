package au.edu.federation.itech3107.studentattendance30395598.entity;

public class Attendance {
    private Integer id;
    private Integer sId;
    private String s_n;
    private String sName;
    private Integer cId;

    private Integer status;

    public String getS_n() {
        return s_n;
    }

    public void setS_n(String s_n) {
        this.s_n = s_n;
    }

    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Attendance(Integer id, Integer sId, String s_n, String sName, Integer cId, Integer status) {
        this.id = id;
        this.sId = sId;
        this.s_n = s_n;
        this.sName = sName;
        this.cId = cId;
        this.status = status;
    }
    public Attendance() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getsId() {
        return sId;
    }
    public void setsId(Integer sId) {
        this.sId = sId;
    }
    public Integer getcId() {
        return cId;
    }
    public void setcId(Integer cId) {
        this.cId = cId;
    }
}