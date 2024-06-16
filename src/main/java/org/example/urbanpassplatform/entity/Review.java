package org.example.urbanpassplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Document(collection = "Reviews")
public class Review extends Auditable {
    @Id
    private String _id;
    private String eventId;
    private String userId;
    private int rating;
    private String comment;
    private Reaction reaction;
    private int likes;

}