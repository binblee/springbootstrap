pipeline {
  agent {
    node {
      label 'k8s'
    }
    
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test '
      }
    }
    stage('Code Quality') {
      steps {
        script {
          try{
            checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
          }catch(e){
            echo e
          }
        }
        
      }
    }
    stage('Image Build&Publish') {
      steps {
        echo 'Build Images'
        script {
          withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'registry2',
            usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
             sh 'docker login -u ${USERNAME} -p ${PASSWORD} registry.cn-hangzhou.aliyuncs.com'    
          } 
          docker.withRegistry("${REGISTRY_ENDPOINT}", "${REGISTRY_CERTS}") {
            sh 'docker build -t ${IMAGE_WITH_TAG} .'
            sh 'docker push ${IMAGE_WITH_TAG}'
          }
        }
        
      }
    }
  }
  environment {
    REGISTRY_ENDPOINT = 'https://registry.cn-hangzhou.aliyuncs.com/v2/'
    IMAGE_WITH_TAG = 'registry.cn-hangzhou.aliyuncs.com/jingshan/springbootstrap'
    REGISTRY_CERTS = 'registry'
  }
  triggers {
    pollSCM('* * * * *')
  }
}
