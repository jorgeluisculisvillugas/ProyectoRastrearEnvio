pipeline {
    agent any
    stages {
        stage('Clonar repositorio') {
            steps {
                git branch: 'main', url: "https://github.com/jorgeluisculisvillugas/ProyectoRastrearEnvio.git"
            }
        }
        stage('Construir aplicación') {
            steps {
                // En Windows usamos "bat" y llamamos al script de Maven con ".cmd"
                bat './mvnw.cmd clean package'
            }
        }
        stage('Ejecutar aplicación') {
            steps {
                // Usamos "bat" en lugar de "sh" para ejecutar el JAR en Windows
                // Cambiamos el nombre del archivo JAR al que se generó realmente
                bat 'java -jar target/RastreoDeEnvio-0.0.1-SNAPSHOT.jar --server.port=8081'

            }
        }
    }
}
