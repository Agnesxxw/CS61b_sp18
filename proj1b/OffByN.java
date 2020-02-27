public class OffByN implements CharacterComparator {
    public int n;

    public OffByN(int N){
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int v1 = Integer.valueOf(x);
        int v2 = Integer.valueOf(y);
        return Math.abs(v1 - v2) == n;
    }
}
