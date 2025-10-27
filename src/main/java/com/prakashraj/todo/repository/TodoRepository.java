package com.prakashraj.todo.repository;

import com.prakashraj.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository  extends JpaRepository<TodoEntity,Long> {
}
