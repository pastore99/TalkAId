# TalkAId
This project allows users to conduct remote speech therapy sessions and analyzes exercises through an Artificial Intelligence (AI) module. The system is based on web technologies, including MySQL for data management, HTML, CSS, and JavaScript for the user interface.

# Installation
Requirements:
* Tomcat Web server v.9.0.83 or above
* MySQL database v.8.0.35 or above

# Technologies:
Languages used: HTML, CSS, JavaScript, Java, Python 
* AI Module for exercise recommendations
* Azure AI Module for vocal exercise evaluations

# Instructions
1. Clone the repository:
bash
```
git clone https://github.com/yourname/speech-therapy-remote.git
```
2. Configure the database:
    * inside /resources/ you must provide a file config.properties with params: db.ur, db.username, db.password used for connecting to your available database.
3. Configure the email service:
    * inside /resources/ you must provide a file email.properties with params: email.string and email.pw used for connecting to your email provider.
4. Configure azure properties:
    * inside /resources/ you must provide a file azure.properties with params: azure.key and azure.region used for connecting to your Azure account.

6. Import the database.sql file into your MySQL database schema. Make sure to use the same schema configured above.
7. Check that your database is working and reachable by other apps.
8. Start the web server
9. Launch the application

# Usage
1. You can log in using the email stored inside the database's table 'user'. All the passwords are "pwd".
2. Navigate inside the website.
3. Follow the instructions to perform the proposed exercise.
4. After completing the exercises, the system will use the Artificial Intelligence module to analyze performance.
5. View the analysis results for feedback and suggestions on your pronunciation and exercise execution.
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
