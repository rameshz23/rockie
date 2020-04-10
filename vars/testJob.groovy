
def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    pipeline {
            options {
                buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
                }
        agent any
        stages {
            stage('This one should be skipped') {
                when {
                    expression { false }
                }
                steps {
                    echo "this should be skipped, but it does not ("
                }
            }
          stage('call') {
           steps    {
                    script  {
                            echo "Welcome to script file"
                    }
            }
          }
          
        }//eod stages
        
        post {
    success {
      script {
        currentBuild.result = 'SUCCESS'
      }
       sparkSend credentialsId: 'webexbot', message: '[$JOB_NAME]($BUILD_URL)', messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'Spark-messg']]
    }  
        
     failure {
      script {
        currentBuild.result = 'FAILURE'
      }
       sparkSend credentialsId: 'webexbot', message: '[$JOB_NAME]($BUILD_URL)', messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'Spark-messg']]
     }
    }         
    } //eod pip
}//eod def
