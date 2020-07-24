package com.crisspian.roombd_class.presenter;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.crisspian.roombd_class.model.Todo;
import com.crisspian.roombd_class.repository.Repository;

import java.util.List;

public class TodoPresenter implements TodoInterfacePresenter {
    private Repository repository;
    private TodoInterfaceView todoInterfaceView;
    private LiveData<List<Todo>> listLiveData;

    public TodoPresenter(Application application, TodoInterfaceView todoInterfaceView) {
        repository = new Repository(application);
        this.todoInterfaceView = todoInterfaceView;
        listLiveData = repository.getAllTodoData();
    }

    @Override
    public void insertTodo(Todo todo) {
        repository.insertTodo(todo);
    }

    @Override
    public void deleteAllTodo() {
        repository.deleteAllTodo();
    }

    @Override
    public LiveData<List<Todo>> getAllLiveData() {
        return listLiveData;
    }


}
