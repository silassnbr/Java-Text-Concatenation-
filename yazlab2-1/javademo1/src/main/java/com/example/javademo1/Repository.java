package com.example.javademo1;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@org.springframework.stereotype.Repository

public interface Repository  extends MongoRepository<Records,Integer> {
    @Query("{recId:'?0'}")
    Records findItemById(Integer recId);
}
