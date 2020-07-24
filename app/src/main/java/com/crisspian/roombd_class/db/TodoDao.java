package com.crisspian.roombd_class.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.crisspian.roombd_class.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTodo(Todo todo);

    @Update
    void update(Todo todo);

    @Query("SELECT * from todo_table ORDER BY id ASC")
     LiveData<List<Todo>> getallTodoFromDB();

    @Delete
    void deleteOneTOdo(Todo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();


}
