package org.example.post.domain.content;

public class PostContent extends Content {

    private static final int MiN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
       super(content);
    }

    @Override
    protected void checkText(String contentText){
        if(contentText == null || contentText.isEmpty()){
            throw new IllegalArgumentException("contentText cannot be null or empty");
        }

        if(contentText.length() > MAX_POST_LENGTH){
            throw new IllegalArgumentException("contentText cannot be longer than " + MAX_POST_LENGTH);
        }
        if(contentText.length() < MiN_POST_LENGTH){
            throw new IllegalArgumentException("contentText cannot be shorter than " + MiN_POST_LENGTH);
        }
    }


}
