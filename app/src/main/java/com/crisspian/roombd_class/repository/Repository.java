package com.crisspian.roombd_class.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.crisspian.roombd_class.db.TodoDao;
import com.crisspian.roombd_class.db.TodoRoomDatabase;
import com.crisspian.roombd_class.model.Todo;

import java.util.List;

public class Repository implements LocalData {

    private TodoDao todoDao;
    private LiveData<List<Todo>> listLiveData;

    public Repository(Application application) {
        TodoRoomDatabase db = TodoRoomDatabase.getDatabase(application);
        todoDao = db.todoDao();
        listLiveData = todoDao.getallTodoFromDB();
    }

    @Override
    public LiveData<List<Todo>> getAllTodoData() {
        return todoDao.getallTodoFromDB();
    }

    @Override
    public void insertTodo(final Todo todo) {
        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.insertTodo(todo);
            }
        });
    }

    @Override
    public void deleteAllTodo() {
        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.deleteAll();
            }
        });

    }
}
