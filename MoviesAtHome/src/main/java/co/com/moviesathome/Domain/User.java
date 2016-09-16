package co.com.moviesathome.Domain;

import java.io.Serializable;

/**
 * Created by estemanp on 19/08/16.
 */
public class User implements Serializable{

    private int id;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private int age;

    public User(String name, String lastName, String userName, String password,int age){
        this.setName(name);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setPassword(password);
        this.setAge(age);
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
