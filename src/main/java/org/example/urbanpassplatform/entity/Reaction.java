package org.example.urbanpassplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Reaction {
    private List<String> userIdList;

    public Reaction() {
        this.userIdList = new ArrayList<>();
    }
    public int getReactionCount() {
        return this.userIdList.size();
    }
}
