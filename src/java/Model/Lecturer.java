package Model;


public class Lecturer extends Account {
    private String department;
    private String meetlink;

    public Lecturer() {
    }

    public Lecturer(String accountID, String name, String email, String password, String department, String meetlink) {
        super(accountID, name, email, password);
        this.department = department;
        this.meetlink = meetlink;
    }

    public Lecturer(String accountID, String name, String email, String password, int roleID, int status, float gender, String phoneNumber, String department, String meetlink) {
        super(accountID, name, email, password, roleID, status, gender, phoneNumber);
        this.department = department;
        this.meetlink = meetlink;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMeetlink() {
        return meetlink;
    }

    public void setMeetlink(String meetlink) {
        this.meetlink = meetlink;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getGender() {
        return gender;
    }

    public void setGender(float gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    
    
    
    
    
}
