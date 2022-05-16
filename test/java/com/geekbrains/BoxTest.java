package com.geekbrains;

import com.geekbrains.lesson4.Box;
import com.geekbrains.lesson4.BoxIsEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoxTest {
    Box box;

    @Nested
    class WhenBoxEmpty {
        @BeforeEach
        void createEmptyBox() {
            box = new Box();
        }

        @Test
        void exceptionWhenTryToShuffleBoxTest() {
            Assertions.assertThrows(NullPointerException.class, () -> box.shuffleBalls());
        }

        @Test
        void addBallTest() {
            box.addBall();
            Assertions.assertEquals(box.getBallsCount(), 1);
        }

        @Nested
        class WhenONeBall {
            @BeforeEach
            void addBall() {
                box.addBall();
            }

            @Test
            void deleteBallTest() throws BoxIsEmptyException {
                box.deleteBall();
                Assertions.assertEquals(box.getBallsCount(), 0);
            }

        }



    }



}
