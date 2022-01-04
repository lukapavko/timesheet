package com.example.tasks.rest_controller;

import com.example.tasks.model.Task;
import com.example.tasks.service.TaskService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/")public class TaskUserController {
    private final TaskService taskUserService;


    @Autowired
    public TaskUserController(TaskService taskUserService) {this.taskUserService = taskUserService;
    }

    @GetMapping(path="/taskUser")
    public List<Task> getAllTaskUsers() {
        return taskUserService.getAllTaskUsers();
    }

    @GetMapping(path = "taskUser/{taskUserId}")
    public Optional<Task> getTaskUserById(@PathVariable("taskUserId")Long taskUserId){
        return taskUserService.findTaskUserById(taskUserId);
    }

    @PostMapping(path = "/taskUser")
    public void createNewTaskUser(@RequestBody(required = false) Task task) {taskUserService.addNewTaskUser(task);
    }

    @DeleteMapping(path="/taskUser/{taskUserId}")
    public void deleteTaskUserById(@PathVariable("taskUserId") Long taskUserId){
        taskUserService.deleteTaskUserById(taskUserId);
    }

    @PutMapping(path="/taskUser/{taskUserId}")
    public void updateTaskUser(@PathVariable("taskUserId") Long taskUserId,
                               @RequestParam(required = false) String taskName){
        taskUserService.updateTaskUser(taskUserId, taskName);
    }


    @GetMapping(value="/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {

        return "This is Home page";
    }
    @GetMapping(value="/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {

        return "Hello there!";
    }
    /*
    @GetMapping( value="/saveCurrentTime")
    @CreationTimestamp
    public @ResponseBody()
    void SaveCurrentTime(HttpServletRequest request) {
        Calendar calendar = new GregorianCalendar(request.getLocale());
        String currentTime = calendar.getTime().toString();
        taskUserService.saveCurrentTime(currentTime, request);
    }
    @GetMapping( value="/CurrentTime")
    @CreationTimestamp
    public @ResponseBody String currentTime(HttpServletRequest request) {
        Calendar calendar = new GregorianCalendar(request.getLocale());
        String currentTime = calendar.getTime().toString();
        return currentTime;
    }
    @PostMapping(path ="/start")
    public String saveCurrentTime(@RequestBody(required = false) Date start_date){
       return taskUserService.startTask();
    }*/
    }
