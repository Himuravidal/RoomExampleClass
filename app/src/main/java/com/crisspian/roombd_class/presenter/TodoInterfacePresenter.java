package com.crisspian.roombd_class.presenter;

import androidx.lifecycle.LiveData;

import com.crisspian.roombd_class.model.Todo;

import java.util.List;

public interface TodoInterfacePresenter {
    void insertTodo(Todo todo);
    void deleteAllTodo();
    LiveData<List<Todo>> getAllLiveData();
}
