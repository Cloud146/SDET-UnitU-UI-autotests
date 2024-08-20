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
                    powershell 'docker-compose ps'
                    powershell 'docker-compose logs selenoid'
                    powershell 'docker-compose logs selenoid-ui'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    powershell 'docker-compose logs test'
                    // Запуск тестов
                    powershell 'docker-compose exec test mvn clean test -P env_docker_selenoid'
                }
            }
        }
        stage('Generate Allure Report') { 
            steps {
                script {
                    // Генерация Allure отчета в корень проекта
                    powershell 'docker-compose exec test allure generate /project/allure-results -o /project/allure-report'
                }
            }
        }
    }
    post {
        always {
            // Архивация артефактов Allure из корневого каталога
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