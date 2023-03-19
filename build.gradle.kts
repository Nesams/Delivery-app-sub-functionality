plugins {
	java
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation ("javax.persistence:javax.persistence-api:2.2")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation("org.projectlombok:lombok:1.18.22")
	implementation ("org.quartz-scheduler:quartz:2.3.2")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	runtimeOnly ("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
