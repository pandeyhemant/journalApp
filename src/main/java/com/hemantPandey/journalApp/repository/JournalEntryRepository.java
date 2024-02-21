package com.hemantPandey.journalApp.repository;

import com.hemantPandey.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId>{
//    MongoRepository<JournalEntry, String>
}
