package com.crisspian.roombd_class.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.crisspian.roombd_class.databinding.ContentMainBinding;
import com.crisspian.roombd_class.model.Todo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class TodoRoomDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

    public static volatile TodoRoomDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TodoRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null){
            synchronized (TodoRoomDatabase.class) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoRoomDatabase.class, "todo_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
