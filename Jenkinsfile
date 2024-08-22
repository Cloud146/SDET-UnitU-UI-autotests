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
					powershell 'docker pull selenoid/vnc:chrome_127.0'
                }
            }
        }
	}
        stage('Build and Run Containers') {
            steps {
                script {
					step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StartService', scale: 1, service: 'selenoid'], useCustomDockerComposeFile: false])
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
                    powershell 'docker-compose -f C:/ProgramData/Jenkins/.jenkins/workspace/Docker2_Test/docker-compose.yml up test'
					step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StopService', service: 'selenoid'], useCustomDockerComposeFile: false]) 
				}
            }
        }
        stage('Generate Allure Report') {
            steps {
                script {
					allure([
						includeProperties: false,
						jdk: '',
						properties: [],
						reportBuildPolicy: 'ALWAYS',
						results: [[path: 'target/allure-results']]
						])
			}
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target//site/allure-maven-plugin/**'
            publishHTML([allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'target/site/allure-maven-plugin',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])
            powershell 'docker-compose down -v'
        }
    }
}