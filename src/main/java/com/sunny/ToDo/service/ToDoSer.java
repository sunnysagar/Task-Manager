package com.sunny.ToDo.service;

import com.sunny.ToDo.model.ToDo;
import com.sunny.ToDo.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service                // to interact to the repository
public class ToDoSer {

//    Simple CURD Operation are  get()/create(), update(), read()/save(), delete()

    @Autowired
    ToDoRepo repo;

    public List<ToDo> getAllToDoItems(){
        ArrayList<ToDo> todoList = new ArrayList<>();
        repo.findAll().forEach(toDo -> todoList.add(toDo));

        return todoList;
    }

    public ToDo getToDoItemById(Long id){

        return repo.findById(id).get();

    }

    public boolean updateStatus(Long id){
        ToDo todo = getToDoItemById(id);
        todo.setStatus("Completed");

        return saveorUpdateToDoItem(todo);

    }

    public boolean saveorUpdateToDoItem(ToDo todo)
    {
        ToDo updatedObj = repo.save(todo);

        return getToDoItemById(updatedObj.getId()) != null;

    }

    public boolean deleteToDoItem(Long id){
        repo.deleteById(id);

        return repo.findById(id).isEmpty();
    }


}
