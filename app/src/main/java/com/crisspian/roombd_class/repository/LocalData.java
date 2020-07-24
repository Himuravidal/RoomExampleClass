package com.crisspian.roombd_class.repository;

import androidx.lifecycle.LiveData;

import com.crisspian.roombd_class.model.Todo;

import java.util.List;

public interface LocalData {
    LiveData<List<Todo>> getAllTodoData();
    void insertTodo(Todo todo);
    void deleteAllTodo();
}
