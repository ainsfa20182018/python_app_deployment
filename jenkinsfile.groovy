node {
    properties([parameters([string(defaultValue: 'IP', description: 'where to build  IP', name: 'Env', trim: false)])])
    stage("Clone repo"){
        git "https://github.com/ainsfa20182018/flask-examples.git"
    }
    stage("Install Reuirments"){
        sh "scp -r *  ec2-user@${ENV}:/tmp"
        sh "ssh ec2-user@${ENV} virtualenv  /tmp/venv"
        sh "ec2-user@${ENV} sudo pip install -r /tmp/requirements.txt"
    }
    stage("Start python app"){
        sh "ec2-user@${ENV} python /tmp/01-hello-world/hello.py"
    }
}