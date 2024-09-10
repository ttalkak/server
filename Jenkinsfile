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
                            cd ${env.WORKSPACE}

                            docker-compose -f docker-compose-prod.yml stop eureka-server || true
                            docker-compose -f docker-compose-prod.yml rm -f eureka-server || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            cd ${env.WORKSPACE}
                            
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

                            // 기존 user-service 컨테이너 중지 및 삭제
                            sh """
                            docker stop gateway-service || true
                            docker rm gateway-service || true
                            docker rmi gateway-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker build -t gateway-service -f gateway-service/Dockerfile.prod ./gateway-service
                            docker rm gateway-service || true
                            docker restart -d --name gateway-service --network spring-network --cpus="0.5" --memory="512m" gateway-service
                            """
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
                            docker build -t user-service -f user-service/Dockerfile.prod ./user-service
                            docker rm user-service || true
                            docker restart -d --name user-service --network spring-network --cpus="0.5" --memory="512m" user-service
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
                                docker stop compute-service || true
                                docker rm compute-service || true
                                docker rmi compute-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                                docker build -t compute-service -f compute-service/Dockerfile.prod ./compute-service
                                docker rm compute-service || true
                                docker restart -d --name compute-service --network spring-network --cpus="0.5" --memory="512m" compute-service
                            """
                        }
                    }
                }


                stage('Build and Deploy Deployment Service') {
                    when {
                        changeset "deployment-service/**"
                    }
                    steps {
                        script {

                            // 기존 compute-service 컨테이너 중지 및 삭제
                            sh """
                                docker stop deployment-service || true
                                docker rm deployment-service || true
                                docker rmi deployment-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                                docker build -t deployment-service -f deployment-service/Dockerfile.prod ./deployment-service
                                docker rm deployment-service || true
                                docker restart -d --name deployment-service --network spring-network --cpus="0.5" --memory="512m" deployment-service
                            """
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
                                docker stop project-service || true
                                docker rm project-service || true
                                docker rmi project-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                                docker build -t project-service -f project-service/Dockerfile.prod ./project-service
                                docker rm project-service || true
                                docker restart -d --name project-service --network spring-network --cpus="0.5" --memory="512m" project-service
                            """
                        }
                    }
                }
            }
        }
    }
}
