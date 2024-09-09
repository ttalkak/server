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
                            def imageName = "${DOCKER_HUB_USERNAME}/eureka-server:${env.BUILD_NUMBER}"
                            def dockerImage = docker.build(imageName, "eureka-server/")
                            docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                                dockerImage.push()
                            }
                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build eureka-server'
                        }
                    }
                }

                stage('Build and Deploy Config Server') {
                    when {
                        changeset "config-server/**"
                    }
                    steps {
                        script {
                            def imageName = "${DOCKER_HUB_USERNAME}/config-server:${env.BUILD_NUMBER}"
                            def dockerImage = docker.build(imageName, "config-server/")
                            docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                                dockerImage.push()
                            }
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
                            def imageName = "${DOCKER_HUB_USERNAME}/gateway-service:${env.BUILD_NUMBER}"
                            def dockerImage = docker.build(imageName, "gateway-service/")
                            docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                                dockerImage.push()
                            }
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
                            def imageName = "${DOCKER_HUB_USERNAME}/user-service:${env.BUILD_NUMBER}"
                            def dockerImage = docker.build(imageName, "user-service/")
                            docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                                dockerImage.push()
                            }
                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build user-service'
                        }
                    }
                }

                stage('Build and Deploy Compute Service') {
                    when {
                        changeset "compute-service/**"
                    }
                    steps {
                        script {
                            def imageName = "${DOCKER_HUB_USERNAME}/compute-service:${env.BUILD_NUMBER}"
                            def dockerImage = docker.build(imageName, "compute-service/")
                            docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                                dockerImage.push()
                            }
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
                            def imageName = "${DOCKER_HUB_USERNAME}/deployment-service:${env.BUILD_NUMBER}"
                            def dockerImage = docker.build(imageName, "deployment-service/")
                            docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                                dockerImage.push()
                            }
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
                            def imageName = "${DOCKER_HUB_USERNAME}/project-service:${env.BUILD_NUMBER}"
                            def dockerImage = docker.build(imageName, "project-service/")
                            docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKER_CREDENTIALS_ID) {
                                dockerImage.push()
                            }
                            // 해당 컨테이너만 재시작
                            sh 'docker-compose -f docker-compose-prod.yml up -d --no-deps --build project-service'
                        }
                    }
                }
            }
        }
    }
}
