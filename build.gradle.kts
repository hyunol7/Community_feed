import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION

plugins {
    id("java")
    kotlin("jvm")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //mysql
   // runtimeOnly("mysql:mysql-connector-java:8.0.32")

    //mariadb
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.2")


    //Lombok
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-gson:0.12.6")

    //querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    //testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    runtimeOnly("com.h2database:h2")

    //kotlin
    implementation(kotlin("stdlib-jdk8"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

// QueryDSL Build Options
val querydslDir = "$buildDir/generated/querydsl"

sourceSets {
    getByName("main").java.srcDir(querydslDir)
}

tasks.withType<JavaCompile>{
    options.generatedSourceOutputDirectory = file(querydslDir)
}

tasks.named("clean") {
    doLast {
     file(querydslDir).deleteRecursively()
    }
}
