package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;
import au.edu.federation.itech3107.studentattendance30395598.entity.Student;

public class Student2Activity extends AppCompatActivity {
    private RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_2);
        Button btnAddStudent = findViewById(R.id.bs);
        btnAddStudent.setOnClickListener(view -> navigateToAddStudent());
        r = findViewById(R.id.rv1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final List<Student> studentList = getStudentData();
        setupRecyclerView(studentList);
    }

    private void navigateToAddStudent() {
        Intent intent = new Intent(Student2Activity.this, Student1Activity.class);
        intent.putExtra("id", getIntent().getIntExtra("id", 0));
        startActivity(intent);
    }

    private List<Student> getStudentData() {
        Integer courseId = getIntent().getIntExtra("id", 0);
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = new Database(this).getReadableDatabase();
        String query = "SELECT id, number, name FROM STUDENT where course_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(courseId)});
        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String number = cursor.getString(1);
                String name = cursor.getString(2);
                Student student = new Student(id, name, number, courseId);
                students.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }

    private void setupRecyclerView(List<Student> studentList) {
        r.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<SVHolder> adapter = new RecyclerView.Adapter<SVHolder>() {
            @NonNull
            @Override
            public SVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = getLayoutInflater().inflate(R.layout.student_2, null);
                return new SVHolder(itemView);
            }

            @Override
            public void onBindViewHolder(@NonNull SVHolder holder, int position) {
                Student student = studentList.get(position);
                holder.tn.setText("Number: " + student.getNumber());
                holder.tn2.setText("Name: " + student.getName());
            }

            @Override
            public int getItemCount() {
                return studentList.size();
            }
        };
        r.setAdapter(adapter);
    }

    public class SVHolder extends RecyclerView.ViewHolder {
        final TextView tn;
        final TextView tn2;

        public SVHolder(View itemView) {
            super(itemView);
            tn = itemView.findViewById(R.id.textStudentNumber);
            tn2 = itemView.findViewById(R.id.textStudentName);
        }
    }
}
