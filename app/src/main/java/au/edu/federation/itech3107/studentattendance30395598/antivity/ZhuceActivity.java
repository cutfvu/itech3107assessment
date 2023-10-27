package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;

public class ZhuceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText etUsername = findViewById(R.id.ett1);
        EditText etPassword = findViewById(R.id.etp1);
        SQLiteDatabase database = new Database(this).getWritableDatabase();
        findViewById(R.id.bs2).setOnClickListener(view -> {
            ContentValues values = new ContentValues();
            values.put("username", etUsername.getText().toString());
            values.put("password", etPassword.getText().toString());
            database.insert("ACCOUNT", null, values);
            Toast.makeText(ZhuceActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}