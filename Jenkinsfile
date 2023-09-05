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
    IMAGE_NAME = "${env.BRANCH_NAME.replace("/", "_")}_${getGitCommitPretty()}"
  }

  stages {
    stage('checkout') {
      steps {
        checkout scm
      }
    }

    stage('prepared') {
      steps {
        sendMessage('🚙 start', 'Nexon NewGame Auction build start')
      }
    }

    stage('build') {
      steps {
        sh 'java -version'
        sh './gradlew clean build --parallel --build-cache'
      }
    }


    stage('ecr login') {
      steps {
        sh '$(aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 961997623925.dkr.ecr.ap-northeast-2.amazonaws.com)'
      }
    }

    stage('docker build && upload') {
      steps {
        sh """docker build -t 961997623925.dkr.ecr.ap-northeast-2.amazonaws.com/sunhwa-producer:${IMAGE_NAME} -f ./Dockerfile ./"""
        sh """
            docker push 961997623925.dkr.ecr.ap-northeast-2.amazonaws.com/sunhwa-producer:${IMAGE_NAME}
            docker rmi 961997623925.dkr.ecr.ap-northeast-2.amazonaws.com/sunhwa-producer:${IMAGE_NAME}
            """
      }
    }
  }

  post {
    success {
      sendMessage('👍 success', 'good')
    }
    failure {
      sendMessage('😭 fail', 'warning')
    }
  }
}
