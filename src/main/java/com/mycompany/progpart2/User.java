package com.mycompany.progpart2;

public class User {
    private String userName;
    private String password;
    private String phoneNum;
    private String firstName;
    private String lastName;

    public User(String userName, String password, String phoneNum, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean isValidUsername() {
        return userName.contains("_") && userName.length() <= 5;
    }

    public boolean isValidPassword() {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@&$!*%?])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    public boolean isValidPhoneNumber() {
        return phoneNum.matches("^\\+\\d{10,13}$");
    }

    public String registerUser() {
        StringBuilder result = new StringBuilder();

        if (!isValidUsername())
            result.append("Username is not correctly formatted.\n");
        else
            result.append("Username has been successfully captured.\n");

        if (!isValidPassword())
            result.append("Password not correctly formatted.\n");
        else
            result.append("Password has been successfully captured.\n");

        if (!isValidPhoneNumber())
            result.append("Phone number is incorrectly formatted.\n");
        else
            result.append("Phone number has been successfully added.\n");

        if (result.toString().contains("not correctly") || result.toString().contains("incorrectly"))
            return result.toString();

        return "Registration successfully completed.";
    }

    public boolean loginUser(String username, String password) {
        return this.userName.equals(username) && this.password.equals(password);
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
