package org.example.urbanpassplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Users")
public class User extends Auditable {
    @Id
    private String _id;
    private String username;
    private String password;
    private String email;
    private String profilePicture;
}