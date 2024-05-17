pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyNotepad"
include(":app")
include(":feature:noteslist")
include(":feature:notedetails")
include(":data")
include(":appnavigation")
include(":feature:auth")
include(":domain")
