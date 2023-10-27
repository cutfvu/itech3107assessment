package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.component.AendHolder;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;
import au.edu.federation.itech3107.studentattendance30395598.entity.Attendance;

public class Attendance2Activity extends AppCompatActivity {
    private EditText dateEditText;
    private RecyclerView recyclerView;
    private SQLiteDatabase db;
    private Integer courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_2);
        db = new Database(this).getReadableDatabase();
        courseId = getIntent().getIntExtra("id", 0);
        dateEditText = findViewById(R.id.ed1);

        findViewById(R.id.btnAddAttendance).setOnClickListener(view -> startAttendance1Activity());

        dateEditText.setOnClickListener(view -> showDatePicker());

        recyclerView = findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void startAttendance1Activity() {
        Intent intent = new Intent(this, Attendance1Activity.class);
        intent.putExtra("id", courseId);
        startActivity(intent);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, selectedYear, selectedMonth, selectedDay) -> {
            updateRecyclerView( selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
            dateEditText.setText(selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateRecyclerView(String selectedDate) {
        final List<Attendance> attendanceList = getAttendanceData(selectedDate);
        RecyclerView.Adapter<AendHolder> adapter = new RecyclerView.Adapter<AendHolder>() {
            @NonNull
            @Override
            public AendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View courseItemView = getLayoutInflater().inflate(R.layout.attendance_3, null);
                return new AendHolder(courseItemView);
            }

            @Override
            public void onBindViewHolder(@NonNull AendHolder holder, int position) {
                Attendance attendance = attendanceList.get(position);
                holder.getT3().setText(attendance.getStatus() == 0 ? "Not checked in" : "Signed in");
                holder.getT1().setText("Number: " + attendance.getS_n());
                holder.getT2().setText("Name: " + attendance.getsName());
            }

            @Override
            public int getItemCount() {
                return attendanceList.size();
            }
        };

        recyclerView.setAdapter(adapter);
    }

    private List<Attendance> getAttendanceData(String selectedDate) {
        List<Attendance> attendances = new ArrayList<>();
        String query = "SELECT id, student_id, student_number, student_name, status FROM ATTENDANCE WHERE course_id = ? AND date = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(courseId), selectedDate});
        if (cursor.moveToFirst()) {
            do {
                attendances.add(createAttendanceFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return attendances;
    }

    private Attendance createAttendanceFromCursor(Cursor cursor) {
        Integer studentId = cursor.getInt(1);
        String studentName = cursor.getString(3);
        Integer status = cursor.getInt(4);
        Integer id = cursor.getInt(0);
        String studentNumber = cursor.getString(2);
        return new Attendance(id, studentId, studentNumber, studentName, courseId, status);
    }
}
