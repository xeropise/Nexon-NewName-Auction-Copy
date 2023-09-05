pipeline {
  agent any
  stages {
    stage('Source') {
      steps {
        git(url: 'https://github.com/xeropise/Nexon-NewName-Auction-Copy.git', branch: 'main', credentialsId: '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')
      }
    }

    stage('Build') {
      steps {
        tool 'gradle'
      }
    }

    stage('Echo') {
      steps {
        sh 'echo "Deploy Success"'
      }
    }

  }
}