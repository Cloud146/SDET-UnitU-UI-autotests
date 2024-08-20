pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
        SELENIUM_ENV = "env_docker_selenoid.xml"
        MAVEN_OPTS = "-Dtestng.dtd.http=true"
    }

    stages {
        stage('Checkout') {
            steps {
                // Проверка кода из репозитория
                git branch: 'CI+Docker', url: 'https://github.com/Cloud146/SDET-UnitU-UI-autotests.git'
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    // Запуск Docker Compose для поднятия окружения с Selenoid
                    powershell '''
                    docker-compose -f ${DOCKER_COMPOSE_FILE} up -d
                    '''

                    // Ожидание запуска Selenoid (опционально, если требуется время для запуска)
                    sleep(time: 20, unit: 'SECONDS')

                    // Запуск тестов в Docker контейнере Maven
                    powershell '''
                    docker-compose -f ${DOCKER_COMPOSE_FILE} run test
                    '''

                    // Остановка и удаление всех контейнеров после выполнения тестов
                    powershell '''
                    docker-compose -f ${DOCKER_COMPOSE_FILE} down
                    '''
                }
            }
        }

        stage('Archive Test Results') {
            steps {
                // Архивация отчетов о тестах
                archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true
                archiveArtifacts artifacts: 'allure-reports/**', allowEmptyArchive: true
                junit 'allure-results/*.xml'
            }
        }

        stage('Cleanup') {
            steps {
                // Остановка всех контейнеров и очистка ресурсов Docker Compose
                powershell '''
                docker-compose -f ${DOCKER_COMPOSE_FILE} down --rmi all -v
                '''
            }
        }
    }

    post {
        always {
            // Удаление любых оставшихся контейнеров
            powershell '''
            docker-compose -f ${DOCKER_COMPOSE_FILE} down --rmi all -v
            '''
        }
        success {
            echo 'Build and tests succeeded!'
        }
        failure {
            echo 'Build or tests failed.'
        }
    }
}