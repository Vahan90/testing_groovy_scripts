job("NodeJS example") {
    scm {
        git("https://github.com/wardviaene/docker-demo.git") { node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName("DSL User")
            node / gitConfigEmail("jenkins-dsl@newtech.academy")
        }
    }
    triggers {
        scm("H/5 * * * *")
    }
    wrappers {
        nodejs("nodejs_16")
    }
    steps {
        shell("npm install")
    }
}
