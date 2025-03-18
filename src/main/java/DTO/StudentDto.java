package DTO;

public class StudentDto {

    private int id;
    private String name;
    private String address;
    private String contact;

    public StudentDto(int id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public StudentDto() {    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getContact() {
        return contact;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }


}
