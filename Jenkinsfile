pipeline {
    agent any
	
	environment {
		MAVEN_OPTS = '-Dmaven.repo.local=/home/app/.m2/repository'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
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
                    powershell 'docker-compose run test-runner mvn clean test'
                }
            }
        }
        stage('Generate Allure Report') {
            steps {
                script {
                    powershell 'docker-compose run test-runner allure generate /app/target/allure-results -o /app/target/allure-report'
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/allure-report/**'
            script {
            publishHTML([allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'target/allure-report',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])
        }
        }
    }
}