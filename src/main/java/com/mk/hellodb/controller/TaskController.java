package com.mk.hellodb.controller;

import com.mk.hellodb.model.Task;
import com.mk.hellodb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TaskController {
    //TaskRepository is an object that will interact with the Task database. so save new Tasks, query for tasks
    private final TaskRepository tasks;
    @Autowired
    public TaskController(TaskRepository tasks){
        this.tasks=tasks;
    }
    //Handles requests to homepage, displays a form of new task. when submitting the form, a POST request will be created to the /addTask
    // route
    @RequestMapping("/")
    public ModelAndView addTask(){
        return new ModelAndView("createTask.html","task", new Task());
    }
    //handles the submission requests, saves the Task sent with the request and redierct to /allTasks
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public RedirectView addNewTask(Task task){
        tasks.save(task);
        return new RedirectView("/allTasks");
    }
    //handles requests to /allTasks
    @RequestMapping("/allTasks")
    public ModelAndView allTasks(ModelMap modelMap){
        modelMap.addAttribute("tasks",tasks.findAllByOrderByUrgentDesc());
        return new ModelAndView("allTasks.html", modelMap);
    }
}
