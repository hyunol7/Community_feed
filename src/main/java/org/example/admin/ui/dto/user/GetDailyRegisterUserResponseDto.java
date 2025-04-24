package org.example.admin.ui.dto.user;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDailyRegisterUserResponseDto {

    private LocalDateTime date;
    private Long count;

    public String getDate() {
        return date != null ? date.toLocalDate().toString() : "";
    }


}
