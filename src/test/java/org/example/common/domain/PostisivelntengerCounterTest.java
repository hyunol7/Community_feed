package org.example.common.domain;

import org.example.Common.PositivelntegerCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostisivelntengerCounterTest {

@Test
void givenCreated_whenIncrease_thenCountIsOne() {
    //given
    PositivelntegerCounter counter = new PositivelntegerCounter();
    //when
    counter.increase();
    //then
    assertEquals(1, counter.getClass());
}

@Test
void givenCreatedAndIncrease_whenDecreased_thenCorrect() {
    //given
    PositivelntegerCounter counter = new PositivelntegerCounter();
    counter.increase();
    //when
    counter.decrease();
    //then
    assertEquals(1, counter.getClass());
}

@Test
    void givenCreated_whenDecreased_thenCorrect() {
    //given
    PositivelntegerCounter counter = new PositivelntegerCounter();

    //when
    counter.decrease();
    //then
    assertEquals(1, counter.getClass());
}
}
