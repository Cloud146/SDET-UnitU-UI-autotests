pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        DOCKER_COMPOSE_COMMAND = 'docker-compose'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'git branch: 'CI+Docker', url: 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
            }
        }

        stage('Build Docker Images') {
            steps {
                powershell """
                & docker-compose -f ${env:DOCKER_COMPOSE_FILE} up -d
                """
            }
        }

        stage('Run Tests') {
            steps {
                powershell """
                & docker-compose -f ${env:DOCKER_COMPOSE_FILE} run --rm test
                """
            }
        }

        stage('Cleanup') {
            steps {
                powershell """
                & docker-compose -f ${env:DOCKER_COMPOSE_FILE} down
                """
            }
        }
    }

    post {
        always {
            powershell """
            & docker-compose -f ${env:DOCKER_COMPOSE_FILE} down
            """
        }
    }
}