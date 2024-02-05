pipeline {
    agent any
    tools {
        maven "apache-maven-3.8.5"
    }
    stages {
        stage('Compile and Clean') {
            steps {
                // Run Maven on a Unix agent.

                sh "mvn clean compile"
            }
        }
        stage('deploy') {

            steps {
                sh "mvn package"
            }
        }
        stage('Build Docker image'){

            steps {
                echo "Welcome to Cart Service"
                sh 'ls'
                sh 'docker build -t  mrahul24/cartfile:${BUILD_NUMBER} .'
            }
        }
        stage('Docker Login'){

            steps {
                 withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u mrahul24 -p ${Dockerpwd}"
                }
            }
        }
        stage('Docker Push'){
            steps {
                sh 'docker push mrahul24/cartfile:${BUILD_NUMBER}'
            }
        }
        stage('Docker deploy'){
            steps {

                sh 'docker run -itd -p  8081:8080 mrahul24/cartfile:${BUILD_NUMBER}'
            }
        }
        stage('Archving') {
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}
