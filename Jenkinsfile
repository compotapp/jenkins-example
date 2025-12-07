pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK-17'
    }

    environment {
        // Имя приложения
        APP_NAME = 'jenkins-example-app'

        // Тег для Docker образа
        DOCKER_TAG = "${APP_NAME}:build-${env.BUILD_NUMBER}"

        // Порт для запуска контейнера
        CONTAINER_PORT = '8081'
    }

    stages {
//         stage('Clean Workspace') {
//             steps {
//                 echo '🧹 Очистка рабочей директории...'
//                 cleanWs()
//             }
//         }

        stage('Check workspace') {
            steps {
                echo "Workspace путь: ${env.WORKSPACE}"
                // или просто:
                echo "Workspace: ${WORKSPACE}"
            }
        }

        stage('Build Java Application') {
            steps {
                echo '🔨 Сборка Java приложения...'
                sh 'mvn clean package'

                script {
                    // Проверяем, что JAR файл создан
                    def jarFiles = findFiles(glob: 'target/*.jar')
                    if (jarFiles) {
                        echo "✅ Создан JAR файл: ${jarFiles[0].name}"
                        env.JAR_FILE = jarFiles[0].name
                    } else {
                        error '❌ JAR файл не создан!'
                    }
                }
            }

            post {
                success {
                    echo '✅ Приложение успешно собрано!'
                }
                failure {
                    echo '❌ Ошибка при сборке приложения'
                }
            }
        }

        stage('Verify Docker') {
            steps {
                echo '🐳 Проверка доступности Docker...'
                script {
                    // Проверяем версию Docker
                    sh 'docker --version'

                    // Проверяем, что можем запустить простую команду
                    sh 'docker ps'

                    echo '✅ Docker доступен!'
                }
            }
        }
//
//         stage('Build Docker Image') {
//             steps {
//                 echo '🏗️  Сборка Docker образа...'
//                 script {
//                     // Проверяем существование Dockerfile
//                     if (!fileExists('Dockerfile')) {
//                         echo '⚠️ Dockerfile не найден, создаем простой...'
//                         writeFile file: 'Dockerfile', text: '''
//                             FROM eclipse-temurin:17-jre-alpine
//                             COPY target/*.jar app.jar
//                             EXPOSE 8080
//                             ENTRYPOINT ["java", "-jar", "app.jar"]
//                         '''
//                     }
//
//                     // Смотрим содержимое Dockerfile для отладки
//                     sh 'cat Dockerfile'
//
//                     // Собираем Docker образ
//                     sh "docker build -t ${DOCKER_TAG} ."
//
//                     // Проверяем, что образ создан
//                     sh "docker images ${APP_NAME}"
//
//                     echo "✅ Docker образ создан: ${DOCKER_TAG}"
//                 }
//             }
//
//             post {
//                 success {
//                     echo '✅ Docker образ успешно собран!'
//                 }
//                 failure {
//                     echo '❌ Ошибка при сборке Docker образа'
//                 }
//             }
//         }
//
//         stage('Stop Old Containers') {
//             steps {
//                 echo '🛑 Остановка старых контейнеров...'
//                 script {
//                     // Останавливаем и удаляем контейнеры с нашим приложением
//                     sh '''
//                         docker ps -a --filter "name=${APP_NAME}" --format "{{.ID}}" | xargs -r docker stop
//                         docker ps -a --filter "name=${APP_NAME}" --format "{{.ID}}" | xargs -r docker rm
//                     '''
//
//                     echo '✅ Старые контейнеры удалены'
//                 }
//             }
//         }
//
//         stage('Run Docker Container') {
//             steps {
//                 echo '🚀 Запуск Docker контейнера...'
//                 script {
//                     // Запускаем контейнер
//                     sh """
//                         docker run -d \
//                           --name ${APP_NAME} \
//                           -p ${CONTAINER_PORT}:8080 \
//                           ${DOCKER_TAG}
//                     """
//
//                     // Проверяем, что контейнер запущен
//                     sh "docker ps --filter 'name=${APP_NAME}'"
//
//                     echo "✅ Контейнер запущен на порту ${CONTAINER_PORT}"
//                 }
//             }
//         }
//
//         stage('Test Application') {
//             steps {
//                 echo '🧪 Тестирование приложения...'
//                 script {
//                     // Ждем пока приложение запустится
//                     echo '⏳ Ожидание запуска приложения (15 секунд)...'
//                     sleep 15
//
//                     // Пробуем сделать запрос к приложению
//                     echo '🔍 Проверка здоровья приложения...'
//                     def healthCheck = sh(
//                         script: "curl -s -o /dev/null -w '%{http_code}' http://localhost:${CONTAINER_PORT}/actuator/health || echo '999'",
//                         returnStdout: true
//                     ).trim()
//
//                     echo "Код ответа от health check: ${healthCheck}"
//
//                     if (healthCheck == '200') {
//                         echo '✅ Приложение успешно запущено и отвечает!'
//
//                         // Дополнительная проверка
//                         sh "curl -f http://localhost:${CONTAINER_PORT}/actuator/health"
//                     } else {
//                         echo "⚠️ Приложение не отвечает корректно. HTTP код: ${healthCheck}"
//
//                         // Смотрим логи контейнера для отладки
//                         sh "docker logs ${APP_NAME} --tail 20"
//
//                         // Не падаем, только предупреждаем
//                         currentBuild.result = 'UNSTABLE'
//                     }
//                 }
//             }
//         }
//
//         stage('Container Info') {
//             steps {
//                 echo '📊 Информация о контейнере...'
//                 script {
//                     // Получаем информацию о контейнере
//                     sh """
//                         echo "=== Docker Container Info ==="
//                         docker ps --filter "name=${APP_NAME}" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
//                         echo ""
//                         echo "=== Docker Image Info ==="
//                         docker images ${APP_NAME} --format "table {{.Repository}}\t{{.Tag}}\t{{.Size}}"
//                         echo ""
//                         echo "=== Приложение доступно по адресу ==="
//                         echo "http://localhost:${CONTAINER_PORT}/actuator/health"
//                         echo "http://localhost:${CONTAINER_PORT}/"
//                     """
//                 }
//             }
//         }
    }

//     post {
//         always {
//             echo '📦 Финализация сборки...'
//             script {
//                 // Сохраняем артефакты
//                 archiveArtifacts artifacts: 'target/*.jar, Dockerfile', fingerprint: true
//
//                 // Сохраняем логи Docker
//                 sh "docker logs ${APP_NAME} --tail 50 > docker-logs.txt 2>&1 || true"
//                 archiveArtifacts artifacts: 'docker-logs.txt', fingerprint: true
//             }
//         }
//         success {
//             echo """
//             🎉 ПАЙПЛАЙН ВЫПОЛНЕН УСПЕШНО!
//
//             📝 Результаты:
//             1. Java приложение собрано: ${env.JAR_FILE}
//             2. Docker образ создан: ${DOCKER_TAG}
//             3. Контейнер запущен: ${APP_NAME}
//             4. Приложение доступно: http://localhost:${CONTAINER_PORT}/
//
//             🔧 Команды для управления:
//             • Просмотр логов: docker logs ${APP_NAME}
//             • Остановка: docker stop ${APP_NAME}
//             • Удаление: docker rm ${APP_NAME}
//             • Список образов: docker images ${APP_NAME}
//             """
//         }
//         failure {
//             echo '❌ Пайплайн завершился с ошибкой'
//
//             script {
//                 // Пытаемся получить логи для отладки
//                 sh "docker logs ${APP_NAME} --tail 100 2>&1 || echo 'Не удалось получить логи контейнера'"
//             }
//         }
//         unstable {
//             echo '⚠️ Пайплайн нестабилен (приложение не отвечает корректно)'
//         }
//     }
}