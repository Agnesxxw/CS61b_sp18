import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    /** new Palindromes, or the autograder might be upset.*/
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome(){
        Boolean b = palindrome.isPalindrome("radar");
        assertEquals(true,b);
        assertFalse(palindrome.isPalindrome("cat")); // Asserts that a condition is false.
    }

    @Test
    public void testOboPalindrome(){
        Deque d= palindrome.wordToDeque("azdb");
        assertFalse(palindrome.isPalindrome(d));
    }

}
