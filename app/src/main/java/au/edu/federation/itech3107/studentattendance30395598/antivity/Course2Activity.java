package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.db.Database;
import au.edu.federation.itech3107.studentattendance30395598.entity.Course;

public class Course2Activity extends AppCompatActivity {
    private RecyclerView rv;
    List<Course> courseList ;

    private   SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new Database(this).getReadableDatabase();
        courseList  = getCourseDataFromDatabase(db);
        setContentView(R.layout.activity_course_3);
        rv = findViewById(R.id.rv1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<CourseHolder> adapter = new CourseListAdapter( getCourseDataFromDatabase(db));
        rv.setAdapter(adapter);
        findViewById(R.id.btnAddCourse).setOnClickListener(view -> startCourse1Activity());
    }

    private List<Course> getCourseDataFromDatabase(SQLiteDatabase db) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT id, name, start_time, end_time FROM COURSE";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String startTime = cursor.getString(2);
                String endTime = cursor.getString(3);
                Course course = new Course(id, name, startTime, endTime);
                courses.add(course);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return courses;
    }

    private void startCourse1Activity() {
        startActivity(new Intent(this, Course1Activity.class));
    }

    private class CourseListAdapter extends RecyclerView.Adapter<CourseHolder> {
        private final List<Course> courseList;

        CourseListAdapter(List<Course> courseList) {
            this.courseList = courseList;
        }

        @NonNull
        @Override
        public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View courseItemView = getLayoutInflater().inflate(R.layout.course_4, null);
            return new CourseHolder(courseItemView,db);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
            Course course = courseList.get(position);
            holder.bindCourseData(course,Course2Activity.this);
        }

        @Override
        public int getItemCount() {
            return courseList.size();
        }
    }
}
