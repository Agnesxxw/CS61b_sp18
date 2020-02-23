# LinkedListDeque{}
## linked relationship
The arrow in class that pointed at an object, the pointer was to the **ENTIRE** object, not a particular field of an object.(in fact it is impossible for a reference to point to the fields of an object in Java)
![addfirst:last.jpg](0)
## The real first node is sentinel.next
## removelast
>* when remove, always from left to right, otherwise, will lose link relationship
```java
Node lp = sentinel.prev.prev;
        lp.next = lp.next.next;
        lp.next.prev = lp;

```
equals to:
```java
sentinel.prev = sentinel.prev.prev
sentinel .prev.next = sentinel
```
# ArrayDeque
## Generic: use cast
```java
items = (typ[]) new Object[8];
```
## Helper methods
quite useful and make codes logically

# % in Java
unlike Python, in Java, the a % b represents:
> (1）a, if a < b （might be negative）
> (2) b if a > b(same as Python)
 
