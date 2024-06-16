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
@Document(collection = "Comments")
public class Comment extends Auditable {
    @Id
    private String _id;
    private String reviewId;
    private String userId;
    private String comment;
    private Reaction reaction;
}
