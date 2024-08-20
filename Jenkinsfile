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
                    // Логи до выполнения команд
                    echo "Stopping existing containers..."
                    powershell 'docker-compose down'
                    
                    echo "Starting containers..."
                    powershell 'docker-compose up --build -d'
                    
                    echo "Checking container status..."
                    powershell 'docker-compose ps'
                    
                    echo "Fetching container logs..."
                    powershell 'docker-compose logs selenoid'
                    powershell 'docker-compose logs selenoid-ui'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    echo "Fetching logs from test container before running tests..."
                    powershell 'docker-compose logs test'
                    
                    echo "Running tests..."
                    powershell 'docker-compose exec test mvn clean test'
                }
            }
        }
    }
    post {
        always {
            echo "Cleaning up containers..."
            powershell 'docker-compose down -v'
        }
    }
}