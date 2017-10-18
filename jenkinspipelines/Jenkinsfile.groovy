#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }

    stage('Run tests') {
        try {
            withMaven(maven: 'Maven 3') {
                dir('jenkinspipelines') {
                    sh 'mvn clean test -Dwebdriver.type=remote -Dwebdriver.url=http://192.168.0.241:4444/wd/hub -Dwebdriver.cap.browserName=chrome'
                }
            }
        } finally {
            junit testResults: 'jenkinspipelines/target/*.xml', allowEmptyResults: true
            archiveArtifacts 'jenkinspipelines/target/**'
        }
    }
}