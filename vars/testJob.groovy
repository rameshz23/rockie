
def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
 //   def param = "${config.Minute} ${config.Hour} ${config.DOM} ${config.MONTH} ${config.DOW} "
    
    pipeline {
        parameters{ text (name: 'DEPLOY_ENV', defaultValue: ' ', description: 'Enter some information about the person') }
        triggers { cron("${DEPLOY_ENV")  }
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
                        echo env.BUILD_NUMBER
                       // echo ${env.BUILD12}
                        echo env.USER
                      //  noti currentBuild.result
                       //noti(currentBuild.currentResult)
                       
                        
                    }
                }
          }
      }//eod   
              
        post {
                always {
                    noti currentBuild.result
                     }
         }  
          
     
        
    } //eod
}//eod
