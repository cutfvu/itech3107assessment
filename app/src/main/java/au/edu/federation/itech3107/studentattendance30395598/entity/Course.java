package au.edu.federation.itech3107.studentattendance30395598.entity;

public class Course {
    private Integer id;
    private String name;
    private String st;
    private String et;



    public void setId(Integer id) {
        this.id = id;
    }

    public Course() {
    }

    public Course(String name, String st, String et) {
        this.name = name;
        this.st = st;
        this.et = et;
    }
    public Course(Integer id, String name, String st, String et) {
        this.id = id;
        this.name = name;
        this.st = st;
        this.et = et;
    }

    public Integer getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSt() {
        return st;
    }
    public void setEt(String et) {
        this.et = et;
    }
    public void setSt(String st) {
        this.st = st;
    }

    public String getEt() {
        return et;
    }

}
