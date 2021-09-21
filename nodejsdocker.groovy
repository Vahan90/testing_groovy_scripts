job("NodeJS_Docker_example") {
    scm {
        git("https://github.com/wardviaene/docker-demo.git") { 
        node->
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
        dockerBuildAndPublish {
            repositoryName("vahan90/docker-nodejs-demo")
            dockerInstallation("main_docker")
            tag('${GIT_REVISION,length=9}')
            registryCredentials("docker-registry")
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
