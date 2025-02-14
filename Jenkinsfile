pipeline 
{
    agent any
    
    stages 
    {
        stage('SeleniumMavenProject') 
        {
            steps 
            {
                echo 'Build the Application and check the window handling script'
            }
        }
        
    }
    post
    {
         always 
         {
            emailext body: 'Summary of the pipeline', subject: 'Pipeline Status', to: 'rushikeshrp049@gmail.com'
         }
    }
}
