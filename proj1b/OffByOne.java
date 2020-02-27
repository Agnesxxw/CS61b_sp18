public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int v1 = Integer.valueOf(x);
        int v2 = Integer.valueOf(y);
        return Math.abs(v1 - v2) == 1;
    }
}
