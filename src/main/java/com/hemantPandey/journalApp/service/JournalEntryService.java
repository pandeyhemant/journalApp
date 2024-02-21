package com.hemantPandey.journalApp.service;

import com.hemantPandey.journalApp.entity.JournalEntry;
import com.hemantPandey.journalApp.entity.User;
import com.hemantPandey.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
             User byUserName = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            byUserName.getJournalEntries().add(saved);
//            byUserName.setUserName(null);
            userService.saveNewUser(byUserName);
        } catch (Exception e){
            System.out.println("Exception " + e);
            throw new RuntimeException("An error occurred while saving the entry =========");
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        try {
            journalEntryRepository.save(journalEntry);
        } catch (Exception e){
            System.out.println("Exception " + e);
        }
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User byUserName = userService.findByUserName(userName);
            removed = byUserName.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if (removed) {
                userService.saveUser(byUserName);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occurred while deleting the entry", e);
        }
        return removed;

    }


}
