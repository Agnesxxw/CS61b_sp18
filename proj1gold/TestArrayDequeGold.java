import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
public class TestArrayDequeGold {
    @Test
    public void testArrDeque() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        /** Establish the deque */
        for (int i = 0; i < 10; i += 1) {
            int number = StdRandom.uniform(100);
            sad.addLast(number);
            ads.addLast(number);
        }
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast():\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }
        /** addFirst */
        for (int i = 0; i < 10; i++) {
            int number = StdRandom.uniform(100);
            sad.addFirst(number);
            ads.addFirst(number);
        }
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast():\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }
        /** RemoveFirst */
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            actualList.add(ads.removeFirst());
            expectedList.add(sad.removeFirst());
        }
        for (int i=0; i<10; i++) { // first check whether ads is the with sad
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!", expected, actual);
        }
        for (int i=0; i<10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!", expected, actual);
        }
        /** RemoveLast */
        actualList.clear();
        expectedList.clear();
        for (int i=0; i<10; i++) {
            actualList.add(ads.removeLast());
            expectedList.add(sad.removeLast());
        }
        int actual = ads.size();
        int expected = sad.size();
        assertEquals("Oh noooo!\nThis is bad in removeLast():\n   actual size " + actual
                        + " not equal to " + expected + "!", expected, actual); // first check the size
        for (int i=0; i<10; i++) {
            assertEquals("Sorry!\nThis is bad in removeLast():\n   Random number " + actualList.get(i)
                            + " not equal to " +  expectedList.get(i) + "!", expectedList.get(i), actualList.get(i));
        }
    }
}
