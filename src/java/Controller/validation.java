/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dal.DAO;

/**
 *
 * @author tanki
 */
public class validation {

    DAO dao = new DAO();

    public String nameValid(String name) {
        String nameErr = "";
        String regex = "^[A-Za-z]+([ -][A-Za-z]+)*$";
        if (name.matches("[0-9]*")) {
            nameErr += "Name must not contains number";
        } else if (!name.matches(regex)) {
            nameErr += "Name must not contains special character nor number";
        }
        return nameErr;
    }

    public String passValid(String password) {
        String passwordError = "";
        if (password.length() < 6) {
            passwordError = " Password must be at least 6 characters long. ";
        } else if (!password.matches(".[A-Z].")) {
            passwordError += " Password must contain at least one uppercase letter. ";
        } else if (!password.matches(".[@#$%^&+=].")) {
            passwordError += " Password must contain at least one special character. ";
        }
        return passwordError;
    }

    public String phoneValid(String phoneNumber) {
        String phoneError = "";
        String regex = "^0\\d{9}$";
        if(!phoneNumber.matches(regex)) {
            phoneError = "Phone Number must start with 0 and contain exactly 10 digits";
        } 
        return phoneError;
    }

    public String emailValid(String email) {
        String emailErr = "";
        String regex = "^[A-Za-z0-9+_.-]+@(fpt|fe)\\.edu\\.vn$";
        if (!email.matches(regex)) {
            emailErr = " Invalid email address format. ";
        } else if (dao.getUser(email) != null) {
            emailErr = " Email address already exists. ";
        }
        return emailErr;
    }
    public String editEmailValid(String email, String oldEmail) {
        String emailErr = "";
        String regex = "^[A-Za-z0-9+_.-]+@(fpt|fe)\\.edu\\.vn$";
        if (!email.matches(regex)) {
            emailErr = " Invalid email address format. ";
        } else if (dao.getUser(email) != null && !email.equals(oldEmail) ) {
            emailErr = " Email address already exists. ";
        }
        return emailErr;
    }

}
