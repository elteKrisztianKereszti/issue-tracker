/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.service;

import hu.elte.alkfejl.issuetracker.model.*;
import hu.elte.alkfejl.issuetracker.repository.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kkereszti
 */

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private IssueRepository issueRepository;

    public Iterable<Message> list() {
        return messageRepository.findAll(); 
    } 

    public Iterable<Message> listByIssue(Issue issue) {
        return messageRepository.findByIssue(issue); 
    } 

    public Message create(Message message, Issue issue) {
        message.setTimestamp(Timestamp.valueOf(LocalDateTime.now())); 
        message.setIssue(issue);
        return messageRepository.save(message);
    }
    
    public Message update(long id, Message message) {
        return messageRepository.save(message);
    }

    public void delete(long id) {
        messageRepository.delete(id);
    }
 
    public Message read(long id) {
        return messageRepository.findOne(id);
    }

}
