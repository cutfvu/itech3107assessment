package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;

public class Course3Activity extends AppCompatActivity {
    private SQLiteDatabase liteDatabase;
    private EditText et1;
    private EditText et2;
    private EditText et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_1);
        Integer cid = getIntent().getIntExtra("id", 0);
        et1 = findViewById(R.id.et3);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et1);
        setupDatePickerDialog(et2);
        setupDatePickerDialog(et3);
        findViewById(R.id.bu1).setOnClickListener(view -> updateCourse(cid));
        findViewById(R.id.bs1).setOnClickListener(view -> openStudentsActivity(cid));
        findViewById(R.id.ba1).setOnClickListener(view -> openAttendanceActivity(cid));

        liteDatabase = new Database(this).getReadableDatabase();
        loadCourseData(cid);
    }

    private void setupDatePickerDialog(EditText editText) {
        editText.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(Course3Activity.this, (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                editText.setText(selectedDate);
            },  calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

    private void updateCourse(Integer courseId) {
        ContentValues values = new ContentValues();
        values.put("name", et1.getText().toString());
        values.put("start_time", et2.getText().toString());
        values.put("end_time", et3.getText().toString());
        liteDatabase.update("COURSE", values, "id = ?", new String[]{String.valueOf(courseId)});
        Toast.makeText(Course3Activity.this, "Save Successful", Toast.LENGTH_SHORT).show();
    }

    private void openStudentsActivity(Integer courseId) {
        Intent intent = new Intent(Course3Activity.this, Student2Activity.class);
        intent.putExtra("id", courseId);
        startActivity(intent);
    }

    private void openAttendanceActivity(Integer courseId) {
        Intent intent = new Intent(Course3Activity.this, Attendance2Activity.class);
        intent.putExtra("id", courseId);
        startActivity(intent);
    }

    private void loadCourseData(Integer courseId) {
        String query = "SELECT id, name, start_time, end_time FROM COURSE where id = ?";
        Cursor cursor = liteDatabase.rawQuery(query, new String[]{String.valueOf(courseId)});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String startTime = cursor.getString(2);
            String endTime = cursor.getString(3);
            et1.setText(name);
            et2.setText(startTime);
            et3.setText(endTime);
        }
        cursor.close();
    }
}
