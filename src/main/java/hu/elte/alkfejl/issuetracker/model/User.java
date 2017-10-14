package hu.elte.alkfejl.issuetracker.model;

import com.fasterxml.jackson.annotation.*;
import java.util.List;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @OneToMany(targetEntity = Issue.class, mappedBy = "user") 
    @JsonIgnoreProperties("user") 
    @JsonIgnore
    private List<Issue> issues; 
    
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        GUEST, USER, ADMIN
    }
}
