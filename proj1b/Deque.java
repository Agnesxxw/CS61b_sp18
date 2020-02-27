public interface Deque<typ> {

    public void addFirst(typ item);
    public void addLast(typ item);
    public boolean isEmpty();
    public int size();
    public typ removeFirst();
    public typ removeLast();
    public typ get(int index);

}


