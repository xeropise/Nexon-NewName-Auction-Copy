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

    stage('Push image') {
      steps {
        script {
          docker.withRegistry('https://166132032896.dkr.ecr.ap-northeast-2.amazonaws.com', 'ecr:ap-northeast-2:Aws-Accesskey') {
            app = docker.build("166132032896.dkr.ecr.ap-northeast-2.amazonaws.com/new_name_auction")
            app.push("latest_${env.BUILD_ID}")
          }

          sh """docker rmi 166132032896.dkr.ecr.ap-northeast-2.amazonaws.com/new_name_auction:latest_${env.BUILD_ID}"""
        }
      }
    }
  }
}
