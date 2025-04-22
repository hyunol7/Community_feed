package org.example.admin.ui.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Common.utils.TimerCalculator;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableResponseDto {

    @Getter
    private Long id;
    @Getter
    private String email;
    @Getter
    private String name;
    @Getter
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt(){
        return TimerCalculator.getFormattedDate(createTime);
    }

    public String getUpdatedAt(){
        return TimerCalculator.getFormattedDate(updateTime);
    }

    public String getLastLoginAt(){
        return TimerCalculator.getFormattedDate(lastLoginAt);
    }
}
