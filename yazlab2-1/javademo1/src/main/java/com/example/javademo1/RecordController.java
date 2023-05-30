package com.example.javademo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {
    @Autowired
    final Repository repository;
    @PostMapping("/added")
    public String saveRecords(@RequestBody Records records){
        repository.save(records);
        return "added";
    }
    public RecordController(Repository repository)
    {
        this.repository=repository;
    }
    public List<Records> findAll()
    {
        return repository.findAll();
    }
    public Records addRec(Records records)
    {
        return repository.save(records);
    }
    public Records update(Records records)
    {
        return repository.save(records);
    }
    public  void delete(Records records)
    {
        repository.delete(records);
    }
}
