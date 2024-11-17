import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

tasks.create("buildDockerImage", DockerBuildImage::class) {
    inputDir.set(layout.projectDirectory)
    images.add("flexbox-sandbox-auth:latest")
    group = "build"
}

tasks.create("build", Task::class) {
    group = "build"
    dependsOn("buildDockerImage")
}