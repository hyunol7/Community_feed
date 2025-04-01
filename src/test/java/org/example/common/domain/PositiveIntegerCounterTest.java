package org.example.common.domain;


import org.example.Common.domain.PositiveIntegerCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveIntegerCounterTest {

@Test
void givenCreated_whenIncrease_thenCountIsOne() {
    //given
    PositiveIntegerCounter counter = new PositiveIntegerCounter();
    //when
    counter.increase();
    //then
    assertEquals(1, counter.getCount());
}

@Test
void givenCreatedAndIncrease_whenDecreased_thenCorrect() {
    //given
    PositiveIntegerCounter counter = new PositiveIntegerCounter()
;    counter.increase();
    //when
    counter.decrease();
    //then
    assertEquals(0, counter.getCount());
}

    @Test
    void givenCreated_whenDecreased_thenCorrect() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount()); // 감소 후 값은 0이어야 함
    }
}
