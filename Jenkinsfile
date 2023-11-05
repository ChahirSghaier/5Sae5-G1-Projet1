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
        stage('Unit Tests') {
             steps {
                 sh 'mvn clean test'  // Utilisez votre commande de build et de test appropriée ici
             }
         }
         stage('Code Quality Analysis') {
             steps {
               sh 'mvn clean verify sonar:sonar -Dsonar.login=admin -Dsonar.password=123456ch
'
             }
         }


    }
}
