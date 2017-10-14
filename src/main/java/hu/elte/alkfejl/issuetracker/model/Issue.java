/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejl.issuetracker.model;

import com.fasterxml.jackson.annotation.*;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author kkereszti
 */
@Entity 
@Table(name = "ISSUES")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(callSuper = true)
public class Issue extends BaseEntity {
    
    @OneToMany(targetEntity = Message.class, mappedBy = "issue")
    @JsonIgnoreProperties("issue")
    @JsonIgnore
    private List<Message> messages;
    
    @JoinColumn
    @ManyToOne(targetEntity = User.class, optional = false)
    @JsonIgnoreProperties("issues")
    private User user;
    
    @Column(nullable = false)
    private Timestamp timestamp;
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
 
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;

    
    public enum Status {
        NEW, INPROGRESS, RESOLVED, CLOSED
    }

}
