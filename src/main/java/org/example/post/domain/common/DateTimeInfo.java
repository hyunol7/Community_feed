package org.example.post.domain.common;

import lombok.Getter;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isEdited;
    @Getter
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }
    public void updateEditDateTime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }
}
