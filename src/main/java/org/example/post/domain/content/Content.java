package org.example.post.domain.content;

import org.example.post.domain.common.DateTimeInfo;

public abstract class Content {
    protected String contentText;
    protected final DateTimeInfo dateTimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.dateTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String updateContent){
        checkText(updateContent);
        this.contentText = updateContent;
        this.dateTimeInfo.updateEditDateTime();
    }


    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
