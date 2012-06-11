name := "MovieAnalyzer"

organization := "com.takanori"

version := "0.1.0"

scalaVersion := "2.9.1"

libraryDependencies += "org.specs2" %% "specs2" % "1.11" % "test"

libraryDependencies += "junit" % "junit" % "4.10"

libraryDependencies += "org.apache.tika" % "tika-core" % "1.1"

libraryDependencies += "org.apache.tika" % "tika-parsers" % "1.1"

initialCommands := "import com.takanori.MovieAnalyzer._"
