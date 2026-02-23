#!/user/bin/env groovy

import com.example.Docker

def call(String image, String tag) {
    return new Docker(this).dockerPush(image, tag)
}