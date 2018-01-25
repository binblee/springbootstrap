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
    stage('Deploy to Test Env') {
      steps {
        script {
          kubernetesDeploy configs: 'kubernetes-deployment-svc.yaml', credentialsType: 'SSH', kubeConfig: [path: ''], secretName: '', ssh: [sshCredentialsId: 'k8s-master1', sshServer: '101.37.109.117'], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
          kubernetesDeploy configs: 'kubernetes-deployment-deploy.yaml', credentialsType: 'SSH', kubeConfig: [path: ''], secretName: '', ssh: [sshCredentialsId: 'k8s-master1', sshServer: '101.37.109.117'], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://']
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
