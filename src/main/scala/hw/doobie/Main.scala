package hw.doobie

import cats.effect.IOApp
import cats.effect._
import cats.implicits._
import cats.data._
import doobie._
import doobie.implicits._
import doobie.util.ExecutionContexts
import concurrent.duration._


// This is just for testing. Consider using cats.effect.IOApp instead of calling
// unsafe methods directly.
import cats.effect.unsafe.implicits.global

case class Person(name: String, age: Int)
val nel = NonEmptyList.of(Person("Bob", 12), Person("Alice", 14))

object Main extends IOApp.Simple {

  // This is your new "main"!
  def run: IO[Unit] =
    //HelloWorld.say().flatMap(IO.println)*>IO(println(io.unsafeRunSync()))
    IO(program2.unsafeRunSync().take(5).foreach(println))*>
    IO.sleep(2.seconds)*>
    IO(println)*>
    IO(program3.unsafeRunSync().foreach(println))
    
  // A transactor that gets connections from java.sql.DriverManager and executes blocking operations
  // on an our synchronous EC. See the chapter on connection handling for more info.
  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",     // driver classname
    "jdbc:postgresql:world",     // connect URL (driver-specific)
    "world",                  // user
    "123"                           // password
  )
  val program1 = 42.pure[ConnectionIO]
  val io = program1.transact(xa)
  val program2 = sql"select code, name, population, gnp from country".query[(String, String, Int, Option[Double])]
                 .stream.take(5).compile.toList.transact(xa)  
  case class Country(code: String, name: String, pop: Int, gnp: Option[Double])
  val program3 = sql"select code, name, population, gnp from country".query[Country].stream.take(5).compile.toList.transact(xa)
}