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
                    powershell 'docker-compose exec test sh -c "rm -rf /project/target"'
                    powershell 'docker-compose exec test mvn clean test'
                }
            }
        }
    }
    post {
        always {
            powershell 'docker-compose down -v'
        }
    }
}