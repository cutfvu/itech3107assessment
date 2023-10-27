package au.edu.federation.itech3107.studentattendance30395598.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String db_name = "COURSE";
    private static final int DATABASE_VERSION = 1;
    public Database(Context context) {
        super(context, db_name, null, DATABASE_VERSION);
    }

    public void createDB1(SQLiteDatabase db){
        db.execSQL("CREATE TABLE COURSE ( id integer primary key autoincrement,           name       varchar(255),           start_time varchar(255),                 end_time   varchar(255) )");
    }

    public void createDB2(SQLiteDatabase db) {
        String str = "CREATE TABLE ACCOUNT( username varchar(255),id integer primary key autoincrement,password varchar(255))";
        db.execSQL(str);
    }


    public void createDB3(SQLiteDatabase db) {
        String sql = "CREATE TABLE STUDENT( id    integer primary key autoincrement,number   varchar(255),course_id   integer,name   varchar(255))";
        db.execSQL(sql);
    }
    public void createDB4(SQLiteDatabase db) {
        String sql = "CREATE TABLE ATTENDANCE( id         integer primary key autoincrement, student_name   varchar(255),status   integer,student_number   varchar(255),"
                + "date   varchar(255),student_id   integer, course_id   varchar(255)";
        db.execSQL(sql);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
       createDB1(db);
       createDB2(db);
       createDB3(db);
       createDB4(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
