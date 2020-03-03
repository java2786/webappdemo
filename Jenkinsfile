pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "mvn3"
   }

   stages {
      stage('Build') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/java2786/webappdemo'

            // Run Maven on a Unix agent.
            // sh "mvn -Dmaven.test.failure.ignore=true clean package"

            // To run Maven on a Windows agent, use
            bat "mvn -Dmaven.test.failure.ignore=true clean package"
         }

         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               deploy adapters: [tomcat7(credentialsId: '325d4bcc-a9cb-45dc-bf5e-82cb4f1b1994', path: '', url: 'http://localhost:8888')], contextPath: 'xyz', war: 'target/*war'
            }
         }
      }
   }
}
