package co.com.moviesathome.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;

import co.com.moviesathome.Domain.User;
import co.com.moviesathome.R;
import co.com.moviesathome.Services.UserRepository;
import co.com.moviesathome.Util.DBHelper;

public class LandingActivity extends AppCompatActivity {

    DBHelper dbHelper;
    UserRepository userRepository;
    TextView tvUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        dbHelper = new DBHelper(getApplicationContext());
        userRepository = new UserRepository(dbHelper);
        tvUsers = (TextView) findViewById(R.id.tvUsers);

        List<User> users = userRepository.getAllUsers();
        String userNames = "Users \n";
        for(User user : users){
            userNames += " " + user.getUserName() + "\n";
        }
        tvUsers.setText(userNames);
    }
}
