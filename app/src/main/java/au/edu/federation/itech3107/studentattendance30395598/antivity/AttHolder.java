package au.edu.federation.itech3107.studentattendance30395598.antivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import au.edu.federation.itech3107.studentattendance30395598.R;

public class AttHolder extends RecyclerView.ViewHolder {
    public CheckBox box;
    public TextView number;
    public TextView name;
    public AttHolder(View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.textStudentNumber);
        name = itemView.findViewById(R.id.textStudentName);
        box = itemView.findViewById(R.id.checkBox);
    }
}