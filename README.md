Synchrony Project
Create a Spring Boot REST app that exposes endpoints to 
• Register a User with basic information, username, and password
• Upload, view and delete images after authorizing the username/password. 
• Associate the updated list of images with the user profile 
• View the User Basic Information and the Images
Leverage the Imgur API i.e. the Spring Boot APP should integrate with Imgur API to upload, view and delete the images
https://apidocs.imgur.com/
App Requirements
• Should use H2 (In-memory database) and JPA to store the user information with user name and password, 
retrieve the user name and password to authenticate the user 
• Integrate with imgur’s API to upload, view and delete images. No need to worry about albums or galleries 
• Create only 1 account at Imgur for the app to integrate. 
• No UI should be built. The requirements should be implemented using the REST API’s. 
Other Notes
• Use GitHub or Bitbucket for your project. 
• Good coding practices (logging, comments, test cases, domain driven design, 3-layer architecture – Controller, 
Service, Repository) are encouraged
• Use of 3rd party libraries are acceptable
• Any additional compile steps needed need to be documented in a README.md
Bonus Points
• Secure API via oAuth2 
• Optimize API for 100K RPM 
• Establish CI/CD Pipeline (feel free to use open-source tools) 
• Create a messaging event that publishes the username and the image name to a Messaging Platform (Ex: Kafka) 
& feel free to connect with a local or cloud instance of the Messaging Platform