package com.crisspian.roombd_class.presenter;

import androidx.lifecycle.LiveData;

import com.crisspian.roombd_class.model.Todo;

import java.util.List;

public interface TodoInterfaceView {

    void showAllTodo(LiveData<List<Todo>> listLiveData);

}
