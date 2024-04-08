package com.sunny.ToDo.controller;

import com.sunny.ToDo.model.ToDo;
import com.sunny.ToDo.service.ToDoSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ToDoController {

    @Autowired
    private ToDoSer service;

    @GetMapping({"/","viewToDoList"})
    public String viewAllToDoItems(Model model, @ModelAttribute("message") String message){
        // to show all existing item on our UI
        model.addAttribute("list", service.getAllToDoItems());
        model.addAttribute("msg", message);

        return "ViewToDoList";   // check


    }

    @GetMapping("/updateToDoStatus/{id}")    // to reference with id
    public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(service.updateStatus(id)){
            redirectAttributes.addFlashAttribute("message", "update success");
            return "redirect:/viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "update failure");
        return "redirect:/viewToDoList";

    }

    @GetMapping("/addToDoItem")
    public String addToDoItem(Model model){
        model.addAttribute("todo", new ToDo());

        return "AddToDoItem";
    }

    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes){
        if(service.saveorUpdateToDoItem(todo)){
            redirectAttributes.addFlashAttribute("message", "save success");
            return "redirect:/viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "save failure");
        return "redirect:/addToDoItem";

    }

    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model){
        model.addAttribute("todo", service.getToDoItemById(id));

        return "EditToDoItem";

    }

    @PostMapping("/editSaveToDOItem")
    public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes){
        if (service.saveorUpdateToDoItem(todo)){
            redirectAttributes.addFlashAttribute("message", "edit success");
            return "redirect:/viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "edit failure");
        return "redirect:/editToDoItem/" + todo.getId();

    }

    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(service.deleteToDoItem(id)){
            redirectAttributes.addFlashAttribute("message", "delete success");
            return "redirect:/viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "delete failure");
        return "redirect:/viewToDoList";

    }



}
