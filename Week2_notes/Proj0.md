# Constructor and Declaration
```java
public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
	    imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
```
# Basic mathmatical calculation 
```java
Math.pow(10, -11);
//10E-11
```
# this
### 1. Using ‘this’ keyword to refer current class instance variables
### 2. Using this() to invoke current class constructor
```java
class Test 
{ 
    int a; 
    int b; 
  
    //Default constructor 
    Test() 
    {   
        this(10, 20); 
        System.out.println("Inside  default constructor \n"); 
    } 
      
    //Parameterized constructor 
    Test(int a, int b) 
    { 
        this.a = a; 
        this.b = b; 
        System.out.println("Inside parameterized constructor"); 
    } 
  
    public static void main(String[] args) 
    { 
        Test object = new Test(); 
    } 
} 
```
### 3. Using ‘this’ keyword to return the current class instance
```java
class Test 
{ 
    int a; 
    int b; 
  
    //Default constructor 
    Test() 
    { 
        a = 10; 
        b = 20; 
    } 
      
    //Method that returns current class instance 
    Test get() 
    { 
        return this; 
    } 
      
    //Displaying value of variables a and b 
    void display() 
    { 
        System.out.println("a = " + a + "  b = " + b); 
    } 
  
    public static void main(String[] args) 
    { 
        Test object = new Test(); 
        object.get().display(); 
    } 
} 

>> a = 10; b = 20
```
### 4. Using ‘this’ keyword as method parameter
```java
void get(){
	display(this);
}
```
# Enhanced loop
for(Declaration : Expression))
```java
for(Planet body : nBody);
```
# i++, ++i
```java
p[i++] = new Planet(xp, yp, vx, vy, m, f);
//p[i] =new Planet(xp, yp, vx, vy, m, f);
//i++;
```
# StdDraw
### 1. Canvas scale and coordinate system.
>* setXscale(double xmin, double xmax)
>* setYscale(double ymin, double ymax)
>* setScale(double min, double max)
### 2. Annotate drawings with text:
>* text(double x, double y, String text)
>* text(double x, double y, String text, double degrees)
>* textLeft(double x, double y, String text)
>* textRight(double x, double y, String text)
## 3. Add images to drawings（or as background）:
>* picture(double x, double y, String filename)
>* picture(double x, double y, String filename, double degrees)
## 4. Clear the background
>* clear()
>* clear(Color color)
## 5. Computer animations and double buffering.
>* enableDoubleBuffering()
>* disableDoubleBuffering()
>* show()
>* pause(int t)
# Static 
### Convenient to be invoked before establishing object
Modified by the static keyword means does not need to create an object to invoke it, it can be accessed directly based on the class.
#### 1. Before method: it is actually the same as a class, which can be called directly by the class name
```java
public class NBody{
	public static Planet[] readPlanets(String fileName){}
	public static void main(String[] args){
		Planet[] pl = NBody.readPlanets(filename);
	}
}
```
#### 2. Static is generally used to modify variables or methods. But there is a special usage that uses static to modify inner classes. Ordinary classes are not allowed to be declared as static.

#### 3. A member variable that is static is called a static variable, also called a class variable, indicating that the variable belongs to this **class**, not an object. A member variable that is not static is called an instance variable, indicating that the variable belongs to a specific object.