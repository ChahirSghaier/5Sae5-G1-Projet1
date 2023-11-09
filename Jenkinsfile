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
         stage('Nettoyage du dépendance ') {
                             steps {
                                 sh 'mvn clean install'
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

    stage('Building image')
    {
           steps{
           sh 'docker build -t chahirsghaier/sghaierchahir-5sea5-g1-station-ski:1.0.0 -f dockerfile .'
           }
           }
    stage('Pushing image to Docker Hub ')
    {
          steps
         {
          sh 'docker push chahirsghaier/sghaierchahir-5sea5-g1-station-ski:1.0.0'
         }
         }

    stage('Executing Docker Compose'){
    steps
        {
        sh 'docker compose up -d'
         }
    }
        stage('Deploying using Nexus ')
         {
         steps{
           sh 'mvn deploy'
         }
         }


}
}
