![LogoVettoriale](https://github.com/pastore99/TalkAId/assets/38082151/47c3370d-c2b0-4ca8-984d-1c2ecabccdac)

# TalkAId
This project allows users to conduct remote speech therapy sessions and analyzes exercises through an Artificial Intelligence (AI) module. The system is based on web technologies. 

The main features are:
* Login page
   * If needed, you can request the reset of the password through the email you registered.    
* Registration page
   * DISCLAIMER: You need a valid license or a valid PIN invitation.   
* Patients have an homepage to chech the exercises to complete.
* Therapists can invite patients to their group.
* Therapists can assign medical conditions and exercises to do.
   * the type of exercises are: crosswords, read-text, images-to-text, read-images, choose-right-text, text-to-images
   * the system recommend exercises to do based on the medical condition of the patient [not integrated, yet](https://github.com/r-monti/fia).
* Therapists can view the points of each done exercise.
* Therapists can view the history points trend on a plot.
* Patients can view the points of each done exercise.
* Patients can submit a feedback after each exercise.
* Patients and their Therapists can message each other.
* Patients and Therapists have a "Notification" chat in which they'll get important news or reminder.
* Therapists can create and delete visiting hours for their medical appointments.
* Patients can book or unbook appointments with their speech therapist.

# Technologies:
Languages used: HTML, CSS, JavaScript, Java, Python 
* AI Module for exercise recommendations
* Azure AI Module for vocal exercise evaluations
# Documentation
For a detailed exploration of our project, please visit our [complete Italian documentation](https://github.com/pastore99/TalkAId/tree/main/projectDocs).

# Instructions for Installing the System
Requirements:
* Tomcat Web server v.9.0.83 or above
* MySQL database v.8.0.35 or above
* Java 17 or above
  
1. Clone the repository:

```bash
git clone https://github.com/pastore99/TalkAId.git
```
2. Configure the database:
    * inside /resources/ you must provide a file **config.properties** with params: db.ur, db.username, db.password used for connecting to your available database.
      
      ```bash
      db.url=jdbc:mysql:yourURL/yourSchema
      db.username=yourUsername
      db.password=yourPassword
      ```
3. Configure the email service:
    * inside /resources/ you must provide a file **email.properties** with params: email.string and email.pw used for connecting to your email provider.
     
      ```bash
      email.string=youremail@gmail.com
      email.pw=YOUR_ONE_TIME_CODE_APPLICATION
      ```
4. Configure azure properties:
    * inside /resources/ you must provide a file **azure.properties** with params: azure.key and azure.region used for connecting to your Azure account.
      
      ```bash
      azure.key=YOUR_KEY
      azure.region=westeurope
      ```

6. Import the [database.sql file](https://github.com/pastore99/TalkAId/blob/main/projectDocs/TalkAId.sql) into your MySQL database. Make sure to use the same schema configured above.
7. Check that your database is working and reachable by other apps.
8. Start the web server
9. Launch the application

# Usage (for testing purposes or functionalities overview)
1. You can log in using the email stored inside the schema's table 'user'. All the passwords are "pwd".
2. Navigate inside the website.
3. Follow the instructions to perform the proposed exercise. (if empty, you can login with the email and password of your therapist and assign them)
4. After completing the exercises, the system will use the Artificial Intelligence module to analyze performance if vocal exercises. [NOTE: the exercises are for Italian language only]
5. View the analysis results for feedback and suggestions on your pronunciation and exercise execution.
If any help needed, consult the user manual provided in the documentation.
# Contributions
  If you wish to contribute to improving this project, follow these steps:
    1. Fork the repository
    2. Create a new branch **(git checkout -b enhancements/feature)**
    3. Commit your changes **(git commit -m 'Add new features')**
    4. Push the branch **(git push origin enhancements/feature)**
    5. Open a **Pull Request**
# License
This project is licensed under the MIT License - see the LICENSE file for details.

# Contact
For further information or questions, contact me via email at pastorecarmine99@gmail.com.

# Acknowledgments
A heartfelt thank you to all contributors and supporters who have played a vital role in bringing this Remote Speech Therapy project to life. Your dedication and collaboration are greatly appreciated.
