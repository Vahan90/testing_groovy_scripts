node {
    def commit_id
    stage("Preparation") {
        checkout scm
        sh "git rev-parse --short HEAD > .git/commit-id"
        commit_id = readFile(".git/commit-id").trim()
    }
    stage("test") {
        nodejs(nodeJSInstallationName: "nodejs_16") {
            sh "npm install --only=dev"
            sh "npm test"
        }
    }
    stage("docker build/push") {
        steps {
            script {
                def dockerHome = tool "main_docker"
                env.PATH = "${dockerHome}/bin:${env.PATH}"
            }
        }
        docker.withRegistry("https://index.docker.io/v1", "dockerhub") {
            def app = docker.build("wardviaene/docker-nodejs-demo:${commit_id}", '.').push()
        }
    }
}
