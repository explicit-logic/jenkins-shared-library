#!/user/bin/env groovy

def call(String dockerRepo, String tag) {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
        sh "docker build -t ${dockerRepo}:${tag} ."
        sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
        sh "docker push ${dockerRepo}:${tag}"
    }
}