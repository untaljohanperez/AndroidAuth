package co.com.moviesathome.Controller;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by lds on 22/08/2016.
 */
public class ApplicationControllerSingleton{

   /* private static ApplicationControllerSingleton instance = null;
    private List<User> users;

    public ApplicationControllerSingleton(){
        users=new ArrayList<User>();
        fillDateBases();
    }

    public static ApplicationControllerSingleton getInstance(){
        if(instance == null)
        {
            instance = new ApplicationControllerSingleton();
        }
        return instance;
    }

    private void fillDateBases(){
        //Add users
        fillUsers();
    }

    private void fillUsers(){
        User person1=new User("Andres","Perez","estemanp","1234",24);
        users.add(person1);

        User person2=new User("Jessica","Ramirez","jessica","1234",16);
        users.add(person2);
    }

    public boolean isValidUser(String username, String password){
        Boolean resp=false;
        for (User person:users) {
            if(username.equals(person.getUserName())){
                if(password.equals(person.getPassword())){
                    resp=true;
                }
            }
        }
        return resp;
    }

    public List<User> getUsers(){
        return users;
    }

    public boolean addUser(User user){
        users.add(user);
        return true;
    }*/

}

