plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	kotlin("plugin.jpa") version "1.9.20"
}

group = "jjfactory"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

	implementation("com.linecorp.kotlin-jdsl:jpql-dsl:3.2.0")
	implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:3.2.0")
	implementation("com.linecorp.kotlin-jdsl:jpql-render:3.2.0")

	testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
	testImplementation("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
kotlin {
	jvmToolchain(17)
}
