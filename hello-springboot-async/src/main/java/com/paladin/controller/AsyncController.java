package com.paladin.controller;

import com.paladin.async.AsyncTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Resource
    private AsyncTask asyncTask;

    @GetMapping("/dealNoReturnTask")
    public void dealNoReturnTask(){
        asyncTask.dealNoReturnTask();
    }

    @GetMapping("/dealHaveReturnTask")
    public String dealHaveReturnTask(){
        Future<String> future = asyncTask.dealHaveReturnTask(100);
        return future.toString();
    }
}