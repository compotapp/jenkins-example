pipeline {
    agent any // Запускать на любом доступном агенте

    tools {
        maven 'Maven' // Указываем, что нужен Maven (его надо настроить в Jenkins глобально позже)
        jdk 'JDK-17'   // Указываем, что нужен JDK 17 (его тоже надо настроить)
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/compotapp/jenkins-example' // Скачиваем код
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile' // Компилируем проект. 'sh' для Unix, если Windows - используйте 'bat'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // Запускаем unit-тесты
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package' // Собираем JAR-файл
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true // Сохраняем артефакт в Jenkins
            }
        }
    }
}