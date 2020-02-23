/**
 * ArrayDeque
 * @author Agnesxxw
 */

public class LinkedListDeque<typ>{

    public class Node{
        public typ ite;
        public Node prev;
        public Node next;

        public Node(typ i, Node p, Node n){
            ite = i;
            prev = p;
            next = n;
        }
    }
    public Node sentinel; // the first item is at sentinel.next if its exists */
    public int size;

    /** Constructor */
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * sentinel.next is the first node.
     */

    /** Adds an item of type typ to the front of the deque. */
    public void addFirst(typ item){
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }
    /** Adds an item of type typ to the back of the deque.*/
    public void addLast(typ item){
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }

    }
    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque(){
        Node pr = sentinel.next;
        while(pr != null){
            System.out.println(pr.ite + " ");
            pr = pr.next;
        }
    }
    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public typ removeFirst(){
        typ f = sentinel.next.ite;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return f;
    }
    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public typ removeLast(){
        typ l = sentinel.prev.ite;
        Node lp = sentinel.prev.prev;
        lp.next = lp.next.next;
        lp.next.prev = lp;
        size -= 1;
        return l;
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    public typ get(int index){
        Node g = sentinel.next;
        for(int i = 0; i < index; i++){
            g = g.next;
        }
        return g.ite;

    }

}
