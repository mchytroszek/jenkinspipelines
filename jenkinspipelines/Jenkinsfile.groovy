#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }

    stage('Run tests') {
        withMaven(maven: 'Maven 3') {
            dir('') {
                sh 'mvn clean test -Dwebdriver.type=chrome -Dwebdriver.chrome.driver=E:/Workspace/chromedriver.exe'
            }
        }
    }
}