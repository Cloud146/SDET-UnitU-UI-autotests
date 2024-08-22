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
		
		stage('Pull browser') {
        steps {
            catchError {
                script {
      	    sh "docker pull selenoid/vnc:chrome_127.0"
                }
            }
        }
        stage('Build and Run Containers') {
            steps {
                script {
					step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StartService', scale: 1, service: 'selenoid'], useCustomDockerComposeFile: false])
                    sh 'docker-compose ps'
                    sh 'docker-compose logs selenoid'
                    sh 'docker-compose logs selenoid-ui'
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    sh 'docker-compose logs test'
                    sh "docker-compose -f /var/jenkins_home/workspace/est/estimate_probation/docker-compose.yml up test"
                }
            }
        }
        stage('Generate Allure Report') {
            steps {
                script {
                    sh 'docker-compose exec test allure generate /project/target/allure-results -o /project/target/allure-report'
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