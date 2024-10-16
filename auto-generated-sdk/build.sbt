lazy val root = (project in file(".")).
  settings(
    organization := "com.factset.analyticsapi",
    name := "engines-sdk",
    version := "6.2.0",
    scalaVersion := "2.11.4",
    scalacOptions ++= Seq("-feature"),
    javacOptions in compile ++= Seq("-Xlint:deprecation"),
    publishArtifact in (Compile, packageDoc) := false,
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++= Seq(
      "io.swagger" % "swagger-annotations" % "1.5.22",
      "org.glassfish.jersey.core" % "jersey-client" % "2.27",
      "org.glassfish.jersey.inject" % "jersey-hk2" % "2.27",
      "org.glassfish.jersey.media" % "jersey-media-multipart" % "2.27",
      "org.glassfish.jersey.media" % "jersey-media-json-jackson" % "2.27",
      "org.glassfish.jersey.connectors" % "jersey-apache-connector" % "2.27",
      "com.fasterxml.jackson.core" % "jackson-core" % "2.10.5" % "compile",
      "com.fasterxml.jackson.core" % "jackson-annotations" % "2.10.5.1" % "compile",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.5.1" % "compile",
      "com.github.joschi.jackson" % "jackson-datatype-threetenbp" % "2.9.10" % "compile",
      "com.brsanthu" % "migbase64" % "2.2",
      "javax.annotation" % "javax.annotation-api" % "1.3.2" % "compile",
      "junit" % "junit" % "4.13.1" % "test",
      "com.novocode" % "junit-interface" % "0.10" % "test"
    )
  )
