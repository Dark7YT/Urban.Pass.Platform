package org.example.urbanpassplatform.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document
public abstract class Auditable {

    // Getters and Setters
    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

}