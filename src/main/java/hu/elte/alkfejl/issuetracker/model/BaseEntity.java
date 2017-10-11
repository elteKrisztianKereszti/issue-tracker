package hu.elte.alkfejl.issuetracker.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private long version;
}
