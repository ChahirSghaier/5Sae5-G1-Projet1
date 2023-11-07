pipeline {
    agent any

    stages {
        stage('Récupération du code source')
        {
            steps
            {
                checkout scm
            }
        }
        stage('Compiling') {
                     steps {
                         sh 'mvn compile'  // Utilisez votre commande de build et de test appropriée ici
                     }
                 }
        stage('Unit Tests') {
             steps {
                 sh 'mvn clean test'  // Utilisez votre commande de build et de test appropriée ici
             }
         }
         stage('Code Quality Analysis') {
             steps {
               sh 'mvn clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=123456ch'
             }
         }
         stage('Deploying using Nexus ')
         {
         steps{
           sh 'mvn deploy'
         }
         }

           stage('Building image'){
           steps{
           sh 'docker build -t sghaierchahir-5sea5-g1-station-ski:1.0.0 -f dockerfile .'
           }
           }
           stage('Running using Docker Compose '){
           steps{
           sh 'docker-compose up -d'
           }
           }

    }
}
