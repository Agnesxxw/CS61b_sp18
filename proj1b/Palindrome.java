public class Palindrome<typ>{

    /** return a Deque where the characters appear in the same order as in the String*/
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i += 1){
            wordDeque.addLast(word.charAt(i)); // charAt returns the char of the index
        }
        return wordDeque;
    }

    /** return true if the given word is a palindrome, and false otherwise */
    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        while(wordDeque.size() != 0) {
            return wordDeque.removeFirst() == wordDeque.removeLast();
        }
        return true; // if it's an empty deque.
        // Also, remember to return in the external }, since the compiler won't be able to know if the return is inside while{}, if{}else{}.....
        // all these control flow may not executed, which won't return -> cause compile error
    }

    /** OffbyOne */
    public boolean isPalindrome(Deque<Character> obo){
        while(obo.size() > 1) {
            OffByOne o = new OffByOne();
            return o.equalChars(obo.removeFirst(), obo.removeLast()) && isPalindrome(obo);
            /** remember add && isPalindrome(obo) to record previous comparison */
        }
        return true;
    }

    /** OffbyN */
    public boolean isPalindrome(Deque<Character> obo, int n){
        while(obo.size() > 1) {
            OffByN o = new OffByN(n);
            return o.equalChars(obo.removeFirst(), obo.removeLast()) && isPalindrome(obo, n);
            /** remember add && isPalindrome(obo) to record previous comparison */
        }
        return true;
    }
}