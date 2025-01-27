package org.example.post.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetPostContentResponseDto extends GetContentResponseDto{
    private Integer commentCount;
    //GetCountResponseDto 에 있는 생성자를 가져온다.
}
