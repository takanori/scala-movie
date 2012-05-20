name := "MovieAnalyzer"

organization := "com.takanori"

version := "0.1.0"

scalaVersion := "2.9.1"

libraryDependencies := Seq(
  "org.specs2" %% "specs2" % "1.7.1" % "test"
)

initialCommands := "import com.takanori.MovieAnalyzer._"
