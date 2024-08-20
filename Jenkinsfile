pipeline {
    agent any

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
                    powershell 'docker-compose up --build'
					// Проверка статуса и логов контейнеров
                    powershell 'docker-compose ps'
                    powershell 'docker-compose logs selenoid'
                    powershell 'docker-compose logs selenoid-ui'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    // Добавляем логи перед запуском тестов
                    powershell 'docker-compose logs test'
                    powershell 'docker-compose exec test mvn clean test'
                }
            }
        }
        stage('Generate Allure Report') {
            steps {
                script {
                    powershell 'docker-compose exec test allure generate /project/target/allure-results -o /project/target/allure-report'
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/allure-report/**'
            publishHTML([allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'target/allure-report',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])
            powershell 'docker-compose down -v'
        }
    }
}