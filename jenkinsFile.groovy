node {
    ansiColor('xterm') {
        // ansi color console output
    }
    timestamps {
        // timestamps console output
    }
    stage('Get code') { 
        git branch: 'develop', credentialsId: 'son_github', url: 'https://github.com/nnson0310/Bankguru_automation_framework'
    }
    stage('Compile code and Run test') { 
        bat 'mvn clean test'
    }
    stage('Generate report') {
        publishHTML([
            allowMissing: false, 
            alwaysLinkToLastBuild: false, 
            keepAll: false, 
            reportDir: 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\bankguru_pipeline_automation_framework\\target\\surefire-reports\\html', 
            reportFiles: 'index.html', 
            reportName: 'TestNG Report', 
            reportTitles: 'TestNG HTML report'
        ])
    }
}