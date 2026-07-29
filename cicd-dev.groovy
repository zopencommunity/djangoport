node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/djangoport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/djangoport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'A high-level Python web framework that encourages rapid development and clean, pragmatic design.'),
      string(name: 'BUILD_LINE', value: 'DEV'),
      booleanParam(name: 'PUBLISH_PYTHON_WHEEL', value: true)
    ]
  }
}
