name := "SocialNetwork"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaJdbc
)     

play.Project.playJavaSettings
