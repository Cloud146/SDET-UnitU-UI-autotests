pipeline {
    agent any

    environment {
        MAVEN_OPTS = '-Dmaven.repo.local=/home/app/.m2/repository'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'CI+Docker', url: 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
            }
        }
        stage('Build and Run Containers') {
            steps {
                script {
                    powershell 'docker-compose down'
                    powershell 'docker-compose up --build -d'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    powershell 'docker-compose exec test-runner mvn clean test'
                }
            }
        }
        stage('Generate Allure Report') {
            steps {
                script {
                    powershell 'docker-compose exec test-runner allure generate /app/allure-results -o /app/allure-report'
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'allure-report/**'
            publishHTML([allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'allure-report',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])
            powershell 'docker-compose down -v'
        }
    }
}