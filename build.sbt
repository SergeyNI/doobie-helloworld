ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file(".")).settings(
  name := "doobie-helloworld",
  libraryDependencies ++= Seq(
    // "core" module - IO, IOApp, schedulers
    // This pulls in the kernel and std modules automatically.
    "org.typelevel" %% "cats-effect" % "3.4.8",
    // "org.typelevel" %% "cats-effect" % "3.3.12",
    // concurrency abstractions and primitives (Concurrent, Sync, Async etc.)
    "org.typelevel" %% "cats-effect-kernel" % "3.4.8",
    // standard "effect" library (Queues, Console, Random etc.)
    "org.typelevel" %% "cats-effect-std" % "3.4.8",
    "org.typelevel" %% "cats-effect-testing-specs2" % "1.4.0" % Test,
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
    "org.tpolecat" %% "doobie-core"      % "1.0.0-RC1",
    "org.tpolecat" %% "doobie-postgres"  % "1.0.0-RC1"          // Postgres driver 42.3.1 + type mappings.
    // "org.tpolecat" %% "doobie-specs2"    % "1.0.0-RC1" % "test", // Specs2 support for typechecking statements.
    // "org.tpolecat" %% "doobie-scalatest" % "1.0.0-RC1" % "test"  // ScalaTest support for typechecking statements.
  )
  
)
