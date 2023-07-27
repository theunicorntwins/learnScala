package twins

object ObjectOrientation extends App {
  // java public static void main(Sring[]) args)
  
  // class and instance
  class Animal {
    // define fields
    val age: Int = 0
    // define methods
    def eat() = println("I'm eating")
  }
  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String) extends Animal // constructor definition

  val aDog = new Dog("Lassie")

  // constructor agruments are NOT fields: need to put a val before the constructor agrument
  aDog.name

  //subtype polymorphism
  val aDeclearedAnimal: Animal = new Dog("Hachi")
  aDeclearedAnimal.eat() // the most derived method will ve called at runtime

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // by default public, can restrict by
    def walk(): Unit
  }

  // "interface" = ulitimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit // valid method name
  }

  // single-class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")

    //override def eat(): Unit = super.eat()

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc.eat(dog)
  aCroc eat aDog // infix notation = object method agrument, only available for methods with ONE agrument
  aCroc ?! "What if we could fly?"

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  }

  /*
    What you tell the complier:

    class Carnivore_Anonymous_35728 extends Carnivore {
      override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
    }

    val dinosaur = new Carnivore_Anonymous_35728
    */
  // singleton object
  object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 53728
    def mySpecialMethods(): Int = 5327
    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethods()
  MySingleton.apply(65)
  MySingleton(65) // equivalent to MySingleton.apply(65)

  object Animal { // companions - companion object
    // companions can access eah other's private fields/ methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields/methods

  /*
    case classes - lightweight data structures with some boilerplate
    - sensible equals and hash code
    - serialization with apply
    - companion with apply
    - pattern matching
   */
  case  class Person(name: String, age: Int)

  // may be constructed without new
  val bob = new Person("Bob", 54) // Person.apply("Bob", 54)

  // exeptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch { // catch(Exception e) {...}
    case e: Exception => "some fulty error message"
  } finally {
    // execute some code no matter what
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with concrete type
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head
  val rest = aList.tail
  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head // string

  // Point #1: in Scala we usually operate with IMMUTABLE values/ objects
  // Any modification to an object must return ANOTHER object
  /*
    Benefits:
    1) works miracles in multithreaded/distributed environment
    2) helps making sense of the code ("reasoning about")
   */
  val reverseList = aList.reverse // return a NEW list

  // Point #2: Scala is closest to the 00 ideal
}
