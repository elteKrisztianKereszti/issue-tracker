/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.api;

import hu.elte.alkfejl.issuetracker.annotation.Role;
import hu.elte.alkfejl.issuetracker.model.*;
import hu.elte.alkfejl.issuetracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hu.elte.alkfejl.issuetracker.model.User.Role.*;

/**
 *
 * @author kkereszti
 */
@RestController
@RequestMapping("/api/messages")
public class MessageApiController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private IssueService issueService;

    @Role({ADMIN, USER})
    @GetMapping("{/issueId}")
    private ResponseEntity<Iterable<Message>> list(@PathVariable long issueId) {
        Iterable<Message> messages = messageService.listByIssue(issueService.read(issueId) );
        return ResponseEntity.ok(messages);
    } 

    @Role({ADMIN, USER})
    @PostMapping("{/issueId}")
    private ResponseEntity<Message> create(@PathVariable long issueId, @RequestBody Message message) {
        
        Message saved = messageService.create(message, issueService.read(issueId));
        return ResponseEntity.ok(saved);
    }

    @Role({ADMIN, USER})
    @GetMapping("/{id}")
    private ResponseEntity<Message> read(@PathVariable int id) {
        Message read = messageService.read(id);
        return ResponseEntity.ok(read);
    }

    @Role(ADMIN)
    @PutMapping("/{id}")
    private ResponseEntity<Message> update(@PathVariable int id, @RequestBody Message message) {
        Message updated = messageService.update(id, message);
        return ResponseEntity.ok(updated);
    }

    @Role(ADMIN)
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable int id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
