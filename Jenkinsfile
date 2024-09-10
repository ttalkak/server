pipeline {
    agent any

    environment {
            DOCKER_REGISTRY = 'docker.io'
            DOCKER_CREDENTIALS_ID = 'dockerhub-access-token' // Jenkins에 저장된 Docker Hub 크리덴셜 ID
            DOCKER_HUB_USERNAME = 'sgo722'
    }
        
    stages {
        stage('Checkout') {
            steps {
                // Git 리포지토리에서 코드를 체크아웃합니다.
                checkout scm
            }
        }

        stage('Build and Deploy Services') {
            parallel {
                stage('Build and Deploy Eureka Server') {
                    when {
                        changeset "eureka-server/**"
                    }
                    steps {
                        script {

                            // 기존 eureka-server 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop eureka-server || true
                            docker-compose -f docker-compose-prod.yml rm -f eureka-server || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker-compose -f docker-compose-prod.yml up -d --no-deps --build eureka-server
                            """
                        }
                    }
                }

                stage('Build and Deploy Config Server') {
                    when {
                        changeset "config-server/**"
                    }
                    steps {
                        script {

                            // 기존 config-server 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop config-server || true
                            docker-compose -f docker-compose-prod.yml rm -f config-server || true
                            """

                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build config-server'
                        }
                    }
                }

                stage('Build and Deploy Gateway Service') {
                    when {
                        changeset "gateway-service/**"
                    }
                    steps {
                        script {

                            // 기존 gateway-service 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop gateway-service || true
                            docker-compose -f docker-compose-prod.yml rm -f gateway-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build gateway-service'
                        }
                    }
                }

                stage('Build and Deploy User Service') {
                    when {
                        changeset "user-service/**"
                    }
                    steps {
                        script {

                            // 기존 user-service 컨테이너 중지 및 삭제
                            sh """
                            docker stop user-service || true
                            docker rm user-service || true
                            docker rmi user-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker build -f user-service/Dockerfile.prod -t user-service
                            docker run -d --name user-service --network spring-network --cpus="0.5" --memory="512m" user-service
                            """
                        }
                    }
                }

                stage('Build and Deploy Compute Service') {
                    when {
                        changeset "compute-service/**"
                    }
                    steps {
                        script {

                            // 기존 compute-service 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop compute-service || true
                            docker-compose -f docker-compose-prod.yml rm -f compute-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build compute-service'
                        }
                    }
                }


                stage('Build and Deploy Deployment Service') {
                    when {
                        changeset "deployment-service/**"
                    }
                    steps {
                        script {

                            // 기존 deployment-service 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop deployment-service || true
                            docker-compose -f docker-compose-prod.yml rm -f deployment-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build deployment-service'
                        }
                    }
                }



                stage('Build and Deploy Project Service') {
                    when {
                        changeset "project-service/**"
                    }
                    steps {
                        script {

                            // 기존 project-service 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop project-service || true
                            docker-compose -f docker-compose-prod.yml rm -f project-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build project-service'
                        }
                    }
                }
            }
        }
    }
}
