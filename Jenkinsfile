pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Git 리포지토리에서 코드를 체크아웃합니다.
                checkout scm
                script {
                    dir('config-server') {
                        sh 'mkdir -p src/main/resources'
                        sh 'cp /home/ubuntu/secret/config/application.yml src/main/resources'
                    }
                }

            }
        }

        stage('Test Services') {
            parallel {
                stage('Test Eureka Server') {
                    when {
                        changeset "eureka-server/**"
                    }
                    steps {
                        script {
                            // Eureka Server 테스트 코드 실행
                            sh """
                            cd eureka-server
                            chmod +x gradlew
                            ./gradlew test
                            """
                        }
                    }
                }

                stage('Test Config Server') {
                    when {
                        changeset "config-server/**"
                    }
                    steps {
                        script {
                            // Config Server 테스트 코드 실행
                            sh """
                            cd config-server
                            chmod +x gradlew
                            ./gradlew test
                            """
                        }
                    }
                }

                stage('Test Gateway Service') {
                    when {
                        changeset "gateway-service/**"
                    }
                    steps {
                        script {
                            // Gateway Service 테스트 코드 실행
                            sh """
                            cd gateway-service
                            chmod +x gradlew
                            ./gradlew test
                            """
                        }
                    }
                }

                stage('Test User Service') {
                    when {
                        changeset "user-service/**"
                    }
                    steps {
                        script {
                            // User Service 테스트 코드 실행
                            sh """
                            cd user-service
                            chmod +x gradlew
                            ./gradlew test
                            """
                        }
                    }
                }

                stage('Test Compute Service') {
                    when {
                        changeset "compute-service/**"
                    }
                    steps {
                        script {
                            // Compute Service 테스트 코드 실행
                            sh """
                            cd compute-service
                            chmod +x gradlew
                            ./gradlew test
                            """
                        }
                    }
                }

                stage('Test Deployment Service') {
                    when {
                        changeset "deployment-service/**"
                    }
                    steps {
                        script {
                            // Deployment Service 테스트 코드 실행
                            sh """
                            cd deployment-service
                            chmod +x gradlew
                            ./gradlew test
                            """
                        }
                    }
                }

                stage('Test Project Service') {
                    when {
                        changeset "project-service/**"
                    }
                    steps {
                        script {
                            // Project Service 테스트 코드 실행
                            sh """
                            cd project-service
                            chmod +x gradlew
                            ./gradlew test
                            """
                        }
                    }
                }
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
                            docker-compose -f docker-compose-prod.yml build --no-cache eureka-server
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
                            sh """
                            docker-compose -f docker-compose-prod.yml build --no-cache config-server
                            docker-compose -f docker-compose-prod.yml up -d --no-deps --build config-server
                            """
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
                            docker-compose -f docker-compose-prod.yml stop gateway-service || true
                            docker-compose -f docker-compose-prod.yml rm -f gateway-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker-compose -f docker-compose-prod.yml build --no-cache gateway-service
                            docker-compose -f docker-compose-prod.yml up -d --no-deps --build gateway-service
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
                            docker-compose -f docker-compose-prod.yml stop user-service || true
                            docker-compose -f docker-compose-prod.yml rm -f user-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker-compose -f docker-compose-prod.yml build --no-cache user-service
                            docker-compose -f docker-compose-prod.yml up -d --no-deps --build user-service
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

                            // 기존 user-service 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop compute-service || true
                            docker-compose -f docker-compose-prod.yml rm -f compute-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker-compose -f docker-compose-prod.yml build --no-cache compute-service
                            docker-compose -f docker-compose-prod.yml up -d --no-deps --build compute-service
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

                            // 기존 user-service 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop deployment-service || true
                            docker-compose -f docker-compose-prod.yml rm -f deployment-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker-compose -f docker-compose-prod.yml build --no-cache deployment-service
                            docker-compose -f docker-compose-prod.yml up -d --no-deps --build deployment-service
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

                            // 기존 user-service 컨테이너 중지 및 삭제
                            sh """
                            docker-compose -f docker-compose-prod.yml stop project-service || true
                            docker-compose -f docker-compose-prod.yml rm -f project-service || true
                            """

                            // 해당 컨테이너만 재시작
                            sh """
                            docker-compose -f docker-compose-prod.yml build --no-cache project-service
                            docker-compose -f docker-compose-prod.yml up -d --no-deps --build project-service
                            """
                        }
                    }
                }
            }
        }
    }
}