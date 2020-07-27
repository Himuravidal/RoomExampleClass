package com.crisspian.roombd_class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crisspian.roombd_class.adapter.TodoAdapter;
import com.crisspian.roombd_class.databinding.FragmentFirstBinding;
import com.crisspian.roombd_class.model.Todo;
import com.crisspian.roombd_class.presenter.TodoInterfaceView;
import com.crisspian.roombd_class.presenter.TodoPresenter;

import java.util.List;

public class FirstFragment extends Fragment implements TodoInterfaceView {

    private FragmentFirstBinding binding;
    private TodoPresenter presenter;
    private TodoAdapter adapter;
    private RecyclerView recyclerView;
    private String newTodo;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new TodoPresenter(getActivity().getApplication(), this);
        if (getArguments() != null) {
          newTodo = getArguments().getString("newTodo");
          Todo todo = new Todo();
          todo.setReminder(newTodo);
          presenter.insertTodo(todo);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container,false);

        recyclerView = binding.rvFragment;
        adapter = new TodoAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        presenter.getAllLiveData().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todoList) {
                adapter.updateData(todoList);
                recyclerView.smoothScrollToPosition(todoList.size());
            }
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void showAllTodo(LiveData<List<Todo>> listLiveData) {

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getContext(), "Borrando todo", Toast.LENGTH_SHORT).show();
            presenter.deleteAllTodo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}