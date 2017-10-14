/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.service;

import hu.elte.alkfejl.issuetracker.model.*;
import hu.elte.alkfejl.issuetracker.model.User.Role;
import hu.elte.alkfejl.issuetracker.repository.IssueRepository;
import java.sql.Timestamp; 
import java.time.LocalDateTime; 
import java.util.Collections; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

/**
 *
 * @author kkereszti
 */
@Service
public class IssueService {
        @Autowired 
        private IssueRepository issueRepository; 

        public Iterable<Issue> listByRole(User user) { 
            Role role = user.getRole(); 
            if (role == Role.USER) { 
                return issueRepository.findAllByUser(user); 
            }  
            else if (role == Role.ADMIN) { 
                return issueRepository.findAll(); 
            } 
            return Collections.emptyList(); 
        } 

        public Issue create(Issue issue, User user) { 
            issue.setStatus(Issue.Status.NEW); 
            issue.setTimestamp(Timestamp.valueOf(LocalDateTime.now())); 
            issue.setUser(user); 
            return issueRepository.save(issue); 
        } 

        public Issue update(long id, Issue issue) { 
            Issue currentIssue = issueRepository.findOne(id); 
            return issueRepository.save(issue); 
        } 


        public void delete(long id) { 
            issueRepository.delete(id); 
        } 

        public Issue read(long id) { 
            return issueRepository.findOne(id); 
        } 

        public Issue create(Issue sue) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
