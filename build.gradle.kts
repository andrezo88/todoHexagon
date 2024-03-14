plugins {
    id("java")
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.abreu"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

ext {
    set("springBootStarterVersion", "3.2.3")
    set("snippetsDir", file("build/generated-snippets"))
    set("lombokVersion", "1.18.30")

}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.2.3")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.3")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.3")
    implementation("com.google.code.gson:gson:2.10.1")

    compileOnly("org.projectlombok:lombok:1.18.30")

    developmentOnly("org.springframework.boot:spring-boot-devtools:3.2.3")

    annotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.3")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    dependsOn(tasks.test)
}
