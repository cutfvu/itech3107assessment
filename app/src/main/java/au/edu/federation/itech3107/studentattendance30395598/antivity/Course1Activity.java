package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;

public class Course1Activity extends AppCompatActivity {
    private Button b1;
    private EditText c1;
    private EditText t1;
    private EditText t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_2);
        initView();
        setupDatePickerDialog(t1);
        setupDatePickerDialog(t2);
        b1.setOnClickListener(view -> addCourseToDatabase());
    }

    private void setupDatePickerDialog(EditText editText) {
        editText.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Course1Activity.this, (datePicker, y, m, d) -> {
                editText.setText(y + "-" + (m + 1) + "-" + d);
            }, year, month, day);

            datePickerDialog.show();
        });
    }
    public void initView(){
        b1 = findViewById(R.id.btnAddCourse);
        c1 = findViewById(R.id.et3);
        t1 = findViewById(R.id.et2);
        t2 = findViewById(R.id.et1);
    }

    private void addCourseToDatabase() {
        SQLiteDatabase db = new Database(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", c1.getText().toString());
        values.put("start_time", t1.getText().toString());
        values.put("end_time", t2.getText().toString());
        db.insert("COURSE", null, values);
        finish();
    }
}
