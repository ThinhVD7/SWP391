package Model;


public class Account {
    public String accountID;
    public String name;
    public String email;
    public String password;
    public int roleID;
    public int status ;
    public float gender;
    public String phoneNumber;

    public Account() {}

    public Account(String accountID, String name, String email, String password, int roleID, int status, float gender, String phoneNumber) {
        this.accountID = accountID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.status = status;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
    
    public Account(String accountID, String name, String email, String password)
    {
        this.accountID = accountID;
        this.name = name;
        this.email = email;
        this.password = password;
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
