plugins {
    id("com.github.node-gradle.node") version "3.5.1"
}

node {
    version = "22.2.0"
    npmVersion = "10.7.0"
    download = true
    workDir = layout.buildDirectory.dir("nodejs")
    npmWorkDir = layout.buildDirectory.dir("npm")
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("build") {
    description = "Runs npm run build"
    group = "build"
    args = listOf("run", "build")
    dependsOn("npmInstall")
}

tasks.register("clean") {
    group = "build"
    doLast {
        val directoriesToDelete = listOf(
            layout.projectDirectory.dir("build").asFile,
            layout.projectDirectory.dir("dist").asFile,
            layout.projectDirectory.dir("node_modules").asFile
        )

        // Delete the directories
        directoriesToDelete.forEach { dir ->
            if (dir.exists()) {
                println("Deleting directory: ${dir.path}")
                delete(dir)
            } else {
                println("Directory not found: ${dir.path}")
            }
        }
    }
}

