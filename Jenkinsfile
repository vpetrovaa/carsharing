pipeline {

    agent any

    tools {
        maven 'Maven'
        dockerTool 'Docker'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }

    stages{

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/vpetrovaa/carsharing.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t vpetrovaa/cars .'
            }
        }

        stage('Login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push vpetrovaa/cars'
            }
        }

        stage('Checkout to k8s repo') {
            steps {
                git branch: 'master', url: 'https://github.com/vpetrovaa/carsharing-k8s.git'
            }
        }

        stage('Update k8s configuration') {
            steps {
                withCredentials([file(credentialsId: 'kubernetes', variable: 'kubernetes')]) {
                sh "kubectl apply -f infra/cars-secrets.yml"
                sh "kubectl apply -f infra/cars-configmap.yml"
                sh "kubectl apply -f infra/cars-deployment.yml"
                sh "kubectl apply -f infra/cars-service.yml"
                }
            }
        }

    }

}