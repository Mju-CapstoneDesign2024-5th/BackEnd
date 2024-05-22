package com.mju.BackEnd.Dto;
public class TestRequest {

    private long id;
    private String personName;
    private int age;

    // 기본 생성자
    public TestRequest() {}

    // 매개변수가 있는 생성자
    public TestRequest(long id, String personName, int age) {
        this.id = id;
        this.personName = personName;
        this.age = age;
    }

    // Getter 및 Setter 메서드
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // toString 메서드 (옵션)
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                ", age=" + age +
                '}';
    }
}
