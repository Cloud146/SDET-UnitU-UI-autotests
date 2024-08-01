pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
            }
        }
        stage('Build and Run Containers') {
            steps {
                script {
                    sh 'docker-compose down'
                    sh 'docker-compose up --build -d'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    sh 'docker-compose run test-runner mvn clean test'
                }
            }
        }
        stage('Generate Allure Report') {
            steps {
                script {
                    sh 'docker-compose run test-runner allure generate /app/target/allure-results -o /app/target/allure-report'
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/allure-report/**'
            publishHTML([reportName: 'Allure Report', reportDir: 'target/allure-report'])
        }
    }
}