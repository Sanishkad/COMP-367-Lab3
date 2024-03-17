pipeline {
    agent any
    
     tools {
        maven 'MAVEN3'
    }

    environment {       
        IMAGE_NAME = "sorada1111/lab3:${BUILD_ID}"
    }

    stages {
        
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/soradaprathan/lab3-Q1.git'
            }
        }

        stage('Build Maven Project') {
            steps {
               bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('Code Coverage') {
            steps {
                 bat 'mvn clean verify jacoco:report'
                 jacoco execPattern: '**/target/jacoco.exec', classPattern: '**/classes', sourcePattern: '**/src/main/java'
            }
        }
        
        stage('Docker Build') {
            steps {
               script {                  
                    bat "docker build -t ${IMAGE_NAME} ."                   
                }
            }
        }
        
        stage('Docker Login') {
            steps {
               script {    
                      withCredentials([usernamePassword(credentialsId: 'dockerhubtoken', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                      bat "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
                      }
                }
            }
        }
        
        stage('Docker Push') {
            steps {
               script {
                     bat "docker push ${IMAGE_NAME}"
                }
            }
        }
    }

    post {
        always {
            // Perform steps like cleaning up or notifying users here
            echo 'Build completed.'
        }
    }
}
