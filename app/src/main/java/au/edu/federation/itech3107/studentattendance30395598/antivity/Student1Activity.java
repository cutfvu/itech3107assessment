package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;

public class Student1Activity extends AppCompatActivity {
    private Button b1;
    private EditText e1;
    private EditText e2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_1);
        db= new Database(Student1Activity.this).getWritableDatabase();
        init();
        b1.setOnClickListener(view -> {
            ContentValues values = new ContentValues();
            values.put("name", e1.getText().toString());
            values.put("number", e2.getText().toString());
            values.put("course_id", getIntent().getIntExtra("id", 0));
            db.insert("STUDENT", null, values);
            finish();
        });
    }
    public void  init(){
        b1 = findViewById(R.id.bs);
        e2 = findViewById(R.id.enu1);
        e1 = findViewById(R.id.ena1);
    }
}