import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator obn = new OffByN(4);
    @Test
    public void testOffByN(){
        assertTrue(obn.equalChars('a', 'e'));
        assertTrue(obn.equalChars('b', 'f'));
        assertTrue(obn.equalChars('f', 'b'));
        assertFalse(obn.equalChars('`', 'b'));
        assertFalse(obn.equalChars('a', 'c'));
        assertFalse(obn.equalChars('&', '%'));
    }
}
