
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
                        try {
        // do something that fails
        sh "exit 1"
        currentBuild.result = 'SUCCESS'
    } catch (Exception err) {
        currentBuild.result = 'FAILURE'
    }
    echo "RESULT: ${currentBuild.result}"    
                    }
            }
          }
          
        }//eod stages
        
        post {
    success {
      script {
        currentBuild.result = 'SUCCESS'
      }
       sparkSend ( credentialsId: 'sparkbot', message: '${JOB_NAME} - ${currentBuild.result} - ${JOB_URL}', messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'common']] )
    }  
        
     failure {
      script {
        currentBuild.result = 'FAILURE'
      }
   sparkSend ( credentialsId: 'sparkbot', message: '${JOB_NAME} - ${currentBuild.result} - ${JOB_URL}', messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'common']] )
     }
    }         
    } //eod pip
}//eod def
