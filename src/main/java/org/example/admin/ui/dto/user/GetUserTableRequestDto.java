package org.example.admin.ui.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Common.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserTableRequestDto extends Pageable {

    private String name;

}
