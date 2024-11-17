plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.svintsov"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    /*prod*/
    compileOnly("org.projectlombok:lombok")
    implementation("org.jetbrains:annotations:26.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.keycloak:keycloak-authz-client:26.0.2")
    implementation("org.postgresql:postgresql:42.7.4")
    runtimeOnly("org.liquibase:liquibase-core:4.29.2")

    /*test*/
    testCompileOnly("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-webflux")

    /*annotation processors*/
    testAnnotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.hibernate.orm:hibernate-jpamodelgen:6.6.1.Final")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("prepareKotlinBuildScriptModel"){}

tasks.register<Copy>("copyFrontendBundle") {
    val frontendBuild = project(":frontend").tasks.named("build")
    dependsOn(frontendBuild)
    from(project(":frontend").layout.projectDirectory.dir("dist"))
    into(file("src/main/resources/static"))
}

tasks.named("processResources") {
    dependsOn("copyFrontendBundle")
}
