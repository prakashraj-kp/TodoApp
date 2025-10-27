package com.prakashraj.todo.controller;


import com.prakashraj.todo.entity.TodoEntity;
import com.prakashraj.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TodoController {

         private final TodoRepository todoRepository;


    @GetMapping({"","/","/home"})
    public String showHomePage(Model model){
        model.addAttribute("todos",todoRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title){
        TodoEntity newTodo= TodoEntity.builder()
                .title(title)
                .completed(false)
                .build();
        todoRepository.save(newTodo);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id){
       TodoEntity existingTodo = todoRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Todo not found: "+id));
       existingTodo.setCompleted(true);
       todoRepository.save(existingTodo);
       return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        TodoEntity existingTodo = todoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Todo not found: "+id));
        todoRepository.delete(existingTodo);
        return "redirect:/";
    }
}
