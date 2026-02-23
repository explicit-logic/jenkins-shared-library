#!/user/bin/env groovy

package com.example

class Docker implements Serializable {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String image, String tag) {
        script.echo "building the docker image..."
        script.sh "docker build -t $image:$tag ."
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'docker', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
            script.sh "echo '${script.PASSWORD}' | docker login -u '${script.USERNAME}' --password-stdin"
        }
    }

    def dockerPush(String image, String tag) {
        script.sh "docker push $image:$tag"
    }
}