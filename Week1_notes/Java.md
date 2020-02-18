# Java
## 1. system.out.print() & system.out.printIn()
## 2. Enhanced loop: 
```java
for(string s :a){} //the string s takes on the identity of each string in a exactly once, starting from a[0] all the way up to a[a.length - 1]
```
## 3. Array initialization
>* declare namspace first
```java
int[] numbers = new int[3]// namespace array_name = new type[n]
```
## 4. **Static Typing** (define type)
>* One of the most important features of Java is that all variables and expressions have a so-called static type. Java variables can contain values of that type, and only that type. Furthermore, the type of a variable can never change.
>* Ad:
>>* Catches certain types of errors, type error will never occur
>>* easier to read and reason about code
>>* code can run efficiently
>* disAd:
>>* Code is more verbose
>>* Less general
>* Since all Java code is part of a class, we must define functions so that they belong to some class. Functions that are part of a class are commonly called "methods".
```java
public static
```
## 5. object-orientated, class(necessary)
## 6. Command Line Tool to run java: 
```java
$ javac HelloWorld.java
$ java HelloWord
$ cat HelloWorld.java // See the content
```
## 7. **class**
>* has been checked type error
>* .class file are simpler for machine to exucute.
>* protect your intellectual property

## 8.Call methods in other class
```java
class.method();
```
>* every file must be associated with some class
>* main method

## 9. Instantiation of class
>* class can not only contains methods, but also data
>* If the method is going to be invoked by an instance of the class, then it should be non-static
**If the method neeeds to use"my instance variables", teh method must be non-static**
```java
package Lab_01;

public class Dogs {
	public int weightInPonds;// Instance variable(Non static)
	
	public Dogs(int w) {
		weightInPonds = w;
	}  // Add a constructor,determin how to instantiate the class. Like def __init__ in Python
	
	public void makeNoise() {// Non-static method,instance method. 
		if(weightInPonds < 10) {
			System.out.println("yip");
		}else{
			System.out.println("bark");
			}
		}
}

```
```java
/** Intatiating an object*/
public class dogLauncher {
	public static void main(String[] args) {
		Dogs smallDog; // Declaration of a dog variable
		new Dogs(25); // Instantiate an object, Dogs class as a Dogs Object(but nobody use it)
		smallDog = new Dogs(5); //instantiation and assignment
		Dogs hugeDog = new Dogs(150);//Declaration, instantiation and assignment
		smallDog.makeNoise();
		hugeDog.makeNoise();//. means member of this class
	}
}
```
>* Arrays of Object
>>* First use the new keyword to create the array
>>* Then use new again for each object that you want to put in the array
```java
Dogs[] dogs = new Dog[2];//Create an array of Dogs of size 2
dogs[0] = new Dogs(8);
dogs[1] = new Dogs(15);
dogs.[0].makeNoise();
```
## 10. Static vs. Non-static
1. Static methods are invoked using the **class name**: Dogs.makeNoise(); (for whole "blueprint")
2. Instance methods are invoked using an **instance name**: maya.makeNoise();(for specific object)
3. Static methods cannot access "my" insatnce varaible, because there is no "me".(no specific object)
4. A class may have a mix of static and non-static members, the member can be instant variable or method
## 11. Invoke relationship between static method and non-static method
1. non-static method -> non-static method, static method -> static method, non-static method -> static method: methods name
2. **static method cannot invoke non-static method**: static method has permanent address when class is defined, but non-static method won't
3. non-static method can invoke static menmer and non-static member, but static method can only invoke static member
## 12. public static void main(String[] args)
1. String[] args is the parameter of main, which can be used to receive parameters by users
```java
$ javac test.java
$ java test a b c // input directly without ()
$ arg is a b c
```
## 13. Libraries
1. For reference: Tutorial online, java official documentations, look at the example code

