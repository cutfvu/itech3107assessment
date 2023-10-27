package au.edu.federation.itech3107.studentattendance30395598.entity;

public class Student {
    private Integer id;
    private String name;
    private String number;
    private Integer c_id;
    public Student(Integer id, String name, String number, Integer c_id) {
        this.id = id;
        this.name = name;
        this.c_id = c_id;
        this.number = number;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
