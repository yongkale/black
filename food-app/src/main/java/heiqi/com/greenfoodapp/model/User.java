package heiqi.com.greenfoodapp.model;

public class User {
    private String id;
    private String name;
    private String password;
    private String role;
    private String idActive;
    private String phone;
    private String[] address;
    private String email;
    private String level;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIdActive(String idActive) {
        this.idActive = idActive;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getIdActive() {
        return idActive;
    }

    public String getPhone() {
        return phone;
    }

    public String[] getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getLevel() {
        return level;
    }
}
