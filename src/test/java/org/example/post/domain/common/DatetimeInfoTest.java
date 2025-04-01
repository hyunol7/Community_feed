package org.example.post.domain.common;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatetimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime localDateTime = dateTimeInfo.getDateTime();

        Thread.sleep(1);

        //when
        dateTimeInfo.updateEditDateTime();

        //then
        assertNotEquals(localDateTime, dateTimeInfo.getDateTime());
        assertTrue(dateTimeInfo.isEdited());


    }
}
