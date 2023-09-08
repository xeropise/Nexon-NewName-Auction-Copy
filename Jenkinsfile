#!/usr/bin/env groovy

def getCommitMsg() {
  return sh(script: """git log --format="%s - %aN" -1""", returnStdout: true)
}

def getGitCommitPretty() {
  return sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'")
}

def sendMessage(emoji, color) {
  def commitMsg = getCommitMsg()
  slackSend(channel: '#서버-젠킨스알림', attachments: [
      [
          color : color,
          title : "${emoji} NEXON-NEWNAME-AUCTION",
          text  : """`${IMAGE_NAME}` <${env.BUILD_URL}|${env.JOB_NAME} (#${env.BUILD_ID})>
${commitMsg}""",
          footer: currentBuild.durationString.replace(' and counting', '')
      ]
    ]
  )
}


pipeline {
  agent any

  tools {
    jdk 'jdk17'
  }

  environment {
    JAVA_HOME = 'tool jdk17'
    IMAGE_NAME = '${env.BRANCH_NAME.replace("/", "_")}_${getGitCommitPretty()}'
  }

  stages {
    stage('checkout') {
      steps {
        checkout scm
      }
    }

    stage('build') {
      steps {
        sh 'java -version'
        sh './gradlew clean build'
      }
    }

    stage('Build image & Push To ECR') {
      steps {
        script {
          docker.withRegistry('https://166132032896.dkr.ecr.ap-northeast-2.amazonaws.com', 'ecr:ap-northeast-2:Aws-Accesskey') {
            app = docker.build("166132032896.dkr.ecr.ap-northeast-2.amazonaws.com/new_name_auction")
            app.push("latest")
          }

          sh """docker rmi 166132032896.dkr.ecr.ap-northeast-2.amazonaws.com/new_name_auction:latest"""
        }
      }
    }

    stage('Deploy AWS') {
      steps {
        script {
          env.EB_APPLICATION_NAME = "New_Name_Auction_BackEnd"
          env.BUCKET_NAME = "elasticbeanstalk-ap-northeast-2-166132032896"
          env.EB_ENV_NAME = "NewNameAuctionBackEnd-env-2"

          sh """whoami"""

          sh """aws s3 cp "./Dockerrun.aws.json" s3://${BUCKET_NAME}/json/${EB_APPLICATION_NAME}-${getGitCommitPretty()}.aws.json \
            --region ap-northeast-2"""

          sleep time: 1000, unit: 'MILLISECONDS'

          sh """
            # Execute Beanstalk
            aws elasticbeanstalk create-application-version \\
                --region ap-northeast-2 \\
                --application-name ${EB_APPLICATION_NAME} \\
                --version-label ${getGitCommitPretty()}-${env.BUILD_ID} \\
                --source-bundle S3Bucket="${BUCKET_NAME}",S3Key="json/${EB_APPLICATION_NAME}-${getGitCommitPretty()}.aws.json"
            """

          sleep time: 1000, unit: 'MILLISECONDS'

          sh """
          aws elasticbeanstalk update-environment \\
              --environment-name ${EB_ENV_NAME} \\
              --region ap-northeast-2 \\
              --version-label ${getGitCommitPretty()}-${env.BUILD_ID}
             """
        }
      }
    }
  }
}
