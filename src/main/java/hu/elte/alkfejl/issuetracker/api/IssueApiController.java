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
@RequestMapping("/api/issues")
public class IssueApiController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Role({ADMIN, USER})
    @GetMapping
    private ResponseEntity<Iterable<Issue>> list() {
        Iterable<Issue> issues = issueService.listByRole(userService.getUser());
        return ResponseEntity.ok(issues);
    } 

    @Role({ADMIN, USER})
    @PostMapping
    private ResponseEntity<Issue> create(@RequestBody Issue issue) {
        Issue saved = issueService.create(issue);
        return ResponseEntity.ok(saved);
    }

    @Role({ADMIN, USER})
    @GetMapping("/{id}")
    private ResponseEntity<Issue> read(@PathVariable int id) {
        Issue read = issueService.read(id);
        return ResponseEntity.ok(read);
    }

    @Role(ADMIN)
    @PutMapping("/{id}")
    private ResponseEntity<Issue> update(@PathVariable int id, @RequestBody Issue issue) {
        Issue updated = issueService.update(id, issue);
        return ResponseEntity.ok(updated);
    }

    @Role(ADMIN)
    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable int id) {
        issueService.delete(id);
        return ResponseEntity.ok().build();
    }
}
