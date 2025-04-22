package org.example.Common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pageable {

    public int pageIndex;
    public int pageSize;

    public Pageable() {
        this.pageIndex = 1;
        this.pageSize = 10;
    }

    public Pageable(int pageIndex, int pageSize) {
        if(pageIndex < 1){
            throw new IllegalArgumentException("페이지의 index는 1이어야 합니다.");
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getOffset(){
        return (pageIndex -1) * pageSize;
    }

    public int getLimit(){
        return pageSize;
    }
}
