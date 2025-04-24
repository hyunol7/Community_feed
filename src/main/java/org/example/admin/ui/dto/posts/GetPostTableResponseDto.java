package org.example.admin.ui.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Common.utils.TimerCalculator;

import java.sql.Time;
import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTableResponseDto {

    @Getter
    private Long postId;
    @Getter
    private Long userId;
    @Getter
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getContent(){
        if(content.length() > 10){
            return content.substring(0, 10) + ",,,";
        }
        return content;
    }
    public String getCreatedAt(){
        return TimerCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimerCalculator.getFormattedDate(updatedAt);
    }

}
