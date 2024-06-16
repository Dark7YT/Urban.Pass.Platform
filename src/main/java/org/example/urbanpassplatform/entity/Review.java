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
    private int likes = 0;

    public Review(String _id, String eventId, String userId, int rating, String comment) {
        this._id = _id;
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public void incrementLikes() {
        this.likes++;
    }
}