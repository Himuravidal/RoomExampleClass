package com.crisspian.roombd_class.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crisspian.roombd_class.databinding.TodoItemListBinding;
import com.crisspian.roombd_class.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<Todo> todoList;

    public TodoAdapter() {
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       TodoItemListBinding binding = TodoItemListBinding
               .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TodoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        String item = todoList.get(position).getReminder();
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        if (todoList != null) {
            return todoList.size();
        } else {
            return 0;
        }
    }
    // update the data
    public void updateData(List<Todo> todos) {
         todoList = todos;
         notifyDataSetChanged();
    }


    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public TodoViewHolder(@NonNull TodoItemListBinding binding) {
            super(binding.getRoot());
            textView = binding.tvTodoItem;
        }
    }

}
