package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;
import au.edu.federation.itech3107.studentattendance30395598.entity.Course;
import au.edu.federation.itech3107.studentattendance30395598.entity.Student;

public class Attendance1Activity extends AppCompatActivity {
    final List<AttHolder> holders = new ArrayList<>();
    private SQLiteDatabase db;
    private Integer integer;
    private List<Student> students ;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_1);
        integer = getIntent().getIntExtra("id", 0);
        db= new Database(Attendance1Activity.this).getWritableDatabase();
        students = queryStudents();
        RecyclerView recyclerView = findViewById(R.id.rv1);
        Button btnSubmit = findViewById(R.id.bt1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AttendanceAdapter adapter = new AttendanceAdapter();
        recyclerView.setAdapter(adapter);
        insert(btnSubmit, null, students, holders);
        Spinner spinner = findViewById(R.id.spinner);
        Course course = loadCourseData(integer);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");

        try {
            ArrayList<String> strings = new ArrayList<>();
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            Date  startDateParsed = sdf.parse(course.getSt());
            Date endDateParsed = sdf.parse(course.getEt());
            startDate.setTime(startDateParsed);
            endDate.setTime(endDateParsed);
            int week = startDate.get(Calendar.DAY_OF_WEEK);
            while (startDate.before(endDate)) {
                if (startDate.get(Calendar.DAY_OF_WEEK) ==week) {
                    strings.add(sdf.format(startDate.getTime()));
                }
                startDate.add(Calendar.DAY_OF_MONTH, 1);
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings.toArray(new String[0]));
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    date = strings.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private Course loadCourseData(Integer courseId) {
        String query = "SELECT id, name, start_time, end_time FROM COURSE where id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(courseId)});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String startTime = cursor.getString(2);
            String endTime = cursor.getString(3);
            cursor.close();
            return new Course(courseId,name,startTime,endTime);
        }
        return null;
    }

    private List<Student> queryStudents() {
        List<Student> students = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT id, number, name FROM STUDENT where course_id = ?", new String[]{String.valueOf(integer)});
        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String number = cursor.getString(1);
                String name = cursor.getString(2);
                Student student = new Student(id, name, number, integer);
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    private void selectTime(EditText time) {
        time.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(Attendance1Activity.this, (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                time.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
            }, year, month, day).show();
        });
    }

    private void insert(Button btnSubmit, EditText time, List<Student> students, List<AttHolder> viewHolderList) {
        btnSubmit.setOnClickListener(view -> {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                AttHolder attHolder = viewHolderList.get(i);
                ContentValues values = new ContentValues();
                values.put("student_name", student.getName());
                values.put("course_id", student.getC_id());
                values.put("date", date);
                values.put("student_id", student.getId());
                values.put("student_number", student.getNumber());
                values.put("status", !attHolder.box.isChecked() ? 0 : 1);
                db.insert("ATTENDANCE", null, values);
            }
            finish();
        });
    }

    private class AttendanceAdapter extends RecyclerView.Adapter<AttHolder> {
        @NonNull
        @Override
        public AttHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            int itemAttendanceStatus = R.layout.attendance_2;
            AttHolder attHolder = new AttHolder(getLayoutInflater().inflate(itemAttendanceStatus, null));
            holders.add(attHolder);
            return attHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AttHolder holder, int position) {
            Student student = students.get(position);
            holder.number.setText("Number: " + student.getNumber());
            holder.name.setText("Name: " + student.getName());
        }

        @Override
        public int getItemCount() {
            return students.size();
        }
    }
}
