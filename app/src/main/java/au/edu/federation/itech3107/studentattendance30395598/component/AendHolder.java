package au.edu.federation.itech3107.studentattendance30395598.component;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import au.edu.federation.itech3107.studentattendance30395598.R;

public class AendHolder extends RecyclerView.ViewHolder {
    TextView t1;
    TextView t2;
    TextView t3;

    public AendHolder(View itemView) {
        super(itemView);

        t1 = itemView.findViewById(R.id.textStudentNumber);
        t2 = itemView.findViewById(R.id.textStudentName);
        t3 = itemView.findViewById(R.id.textStatus);

    }

    public void setT1(TextView t1) {
        this.t1 = t1;
    }

    public void setT2(TextView t2) {
        this.t2 = t2;
    }

    public void setT3(TextView t3) {
        this.t3 = t3;
    }

    public TextView getT1() {
        return t1;
    }

    public TextView getT2() {
        return t2;
    }

    public TextView getT3() {
        return t3;
    }
}
