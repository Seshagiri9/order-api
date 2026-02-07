pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/Seshagiri9/order-api.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t order-api .'
            }
        }

        stage('Push to ECR') {
            steps {
                sh 'docker tag order-api:latest 588106421023.dkr.ecr.ap-south-1.amazonaws.com/order-api:latest'
                sh ' push 588106421023.dkr.ecr.ap-south-1.amazonaws.com/order-api:latest'
            }
        }

        stage('Deploy ECS') {
            steps {
                sh 'aws ecs update-service --cluster order-cluster --service order-api --force-new-deployment'
            }
        }
    }
}
