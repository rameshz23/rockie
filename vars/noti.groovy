def call(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus = buildStatus ?: 'SUCCESS'
if ( buildStatus == "SUCCESS" ) {
 // old sparkSend ( credentialsId: 'sparkbot',  message: ' success - ${JOB_NAME}-${BUILD_NUMBER}-${currentBuild.currentResult} - ${BUILD_RESULT} - ${JOB_URL}', messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'common']] )  
 sparkSend ( credentialsId: 'sparkbot',  message: ' success - ${JOB_NAME}-${BUILD_NUMBER}-${currentBuild.currentResult} - ${BUILD_RESULT} - ${JOB_URL}', messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'common']] ) 
}
  else if( buildStatus == "FAILURE" ) { 
sparkSend ( credentialsId: 'sparkbot',  message: ' success - ${JOB_NAME}-${BUILD_NUMBER}-${currentBuild.currentResult} - ${BUILD_RESULT} - ${JOB_URL}', messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'common']] )
  }
  
 //color-  sparkSend ( credentialsId: 'sparkbot', color: '#FFFF00', message: "${env.JOB_NAME} - [${env.BUILD_NUMBER}] - (${env.BUILD_URL})", messageType: 'markdown', spaceList: [[spaceId: '768a8310-7348-11ea-8dca-b5cc1c3a792c', spaceName: 'common']] )
}
