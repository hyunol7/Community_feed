package org.example.common.domain;

import org.example.Common.PostisivelntengerCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostisivelntengerCounterTest {

@Test
void givenCreated_whenIncrease_thenCountIsOne() {
    //given
    PostisivelntengerCounter counter = new PostisivelntengerCounter();
    //when
    counter.increase();
    //then
    assertEquals(1, counter.getClass());
}

@Test
void givenCreatedAndIncrease_whenDecreased_thenCorrect() {
    //given
    PostisivelntengerCounter counter = new PostisivelntengerCounter();
    counter.increase();
    //when
    counter.descrease();
    //then
    assertEquals(1, counter.getClass());
}

@Test
    void givenCreated_whenDecreased_thenCorrect() {
    //given
    PostisivelntengerCounter counter = new PostisivelntengerCounter();

    //when
    counter.descrease();
    //then
    assertEquals(1, counter.getClass());
}
}
