package org.example.urbanpassplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@Document(collection = "Users")
public class User extends Auditable {
    @Id
    private String _id;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private List<Role> roles;
    private List<Ticket> tickets;
    public User() {
        this.tickets = new ArrayList<>();
    }
}