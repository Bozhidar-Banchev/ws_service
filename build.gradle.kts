plugins {
  java
  id("org.springframework.boot") version "2.7.10"
  id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-websocket")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  implementation("com.google.code.gson:gson:2.10.1")
  implementation("org.java-websocket:Java-WebSocket:1.5.3")
  implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")

  //Mapstruct
  implementation("org.mapstruct:mapstruct:1.5.3.Final")
  annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

}

tasks.withType<Test> {
  useJUnitPlatform()
}
