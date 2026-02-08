pipeline {
    agent any
    tools {
        // Changed from '21' to 'Java Home'
        jdk 'Java Home'

        // Changed from 'maven9' to 'Maven 3.9.12'
        maven 'Maven 3.9.12'
    }
    stages {
        stage('Check out') {
            steps {
                git branch: 'main', url: 'https://github.com/Seshagiri9/order-api.git'
            }
        }

        stage('Build') {
                    steps {
                        bat 'mvn clean install -DskipTests'
                    }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t order-api .'
            }
        }

        stage('Push to ECR') {
                    steps {
                        withCredentials([[
                            $class: 'AmazonWebServicesCredentialsBinding',
                            credentialsId: 'aws-credentials-id',
                            accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                            secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                        ]]) {
                            // Use double quotes (") so the variables are correctly injected into the command
                            bat "aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 588106421023.dkr.ecr.ap-south-1.amazonaws.com"

                            bat 'docker tag order-api:latest 588106421023.dkr.ecr.ap-south-1.amazonaws.com/order-api:latest'
                            bat 'docker push 588106421023.dkr.ecr.ap-south-1.amazonaws.com/order-api:latest'
                        }
                    }
                }

        stage('Deploy ECS') {
            steps {
                bat 'aws ecs update-service --cluster order-cluster --service order-api --force-new-deployment'
            }
        }
    }
}