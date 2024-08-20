pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        DOCKER_COMPOSE_COMMAND = 'docker-compose'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'CI+Docker', url: 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    bat """
                    docker-compose -f ${DOCKER_COMPOSE_FILE} up -d
                    """
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    bat """
                    docker-compose -f ${DOCKER_COMPOSE_FILE} run --rm test
                    """
                }
            }
        }

        stage('Cleanup') {
            steps {
                script {
                    bat """
                    docker-compose -f ${DOCKER_COMPOSE_FILE} down
                    """
                }
            }
        }
    }

    post {
        always {
            script {
                bat """
                docker-compose -f ${DOCKER_COMPOSE_FILE} down
                """
            }
        }
    }
}