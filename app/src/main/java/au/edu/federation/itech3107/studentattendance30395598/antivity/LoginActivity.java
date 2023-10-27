package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.ett1);
        etPassword = findViewById(R.id.etp1);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnSignup = findViewById(R.id.bs2);

        btnLogin.setOnClickListener(view -> handleLogin());
        btnSignup.setOnClickListener(view -> navigateToSignup());
    }

    private void handleLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        SQLiteDatabase db = new Database(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username, password FROM ACCOUNT WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.moveToFirst()) {
            navigateToCourseList();
        } else {
            displayLoginFailMessage();
        }
    }

    private void navigateToCourseList() {
        Intent intent = new Intent(LoginActivity.this, Course2Activity.class);
        startActivity(intent);
    }

    private void displayLoginFailMessage() {
        Toast.makeText(this, "Login failed. Please check your username and password.", Toast.LENGTH_SHORT).show();
    }

    private void navigateToSignup() {
        Intent intent = new Intent(LoginActivity.this, ZhuceActivity.class);
        startActivity(intent);
    }
}
