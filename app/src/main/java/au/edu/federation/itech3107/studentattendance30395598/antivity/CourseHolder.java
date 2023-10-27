package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import au.edu.federation.itech3107.studentattendance30395598.R;
import au.edu.federation.itech3107.studentattendance30395598.entity.Course;

public class CourseHolder extends RecyclerView.ViewHolder {
        TextView tn;
        TextView ts;
        TextView te;
        Button btn;

    SQLiteDatabase db;

        public CourseHolder(View itemView, SQLiteDatabase db) {
            super(itemView);
            tn = itemView.findViewById(R.id.t1);
            ts = itemView.findViewById(R.id.t2);
            te = itemView.findViewById(R.id.t3);
            btn = itemView.findViewById(R.id.b1);
            this.db = db;
        }

        public void bindCourseData(Course course, Course2Activity course2Activity){
            tn.setText("Course Name: " + course.getName());
            ts.setText("Start Date: " + course.getSt());
            te.setText("End Date: " + course.getEt());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(course2Activity, Course3Activity.class);
                intent.putExtra("id", course.getId());
                course2Activity.startActivity(intent);
            });
            btn.setOnClickListener(v -> {
                db.delete("COURSE", "id = ?", new String[]{String.valueOf(course.getId())});
                course2Activity.recreate();
            });
        }
    }