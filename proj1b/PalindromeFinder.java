/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;

        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            Deque wordInDeque = palindrome.wordToDeque(word);
            if (word.length() >= minLength && palindrome.isPalindrome(wordInDeque, 3)) {
                System.out.println(word);
            }
        }
    }
}
