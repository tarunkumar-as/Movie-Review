# Movie-Review
The movie review system is one of the widely used system in the current world. Before proceeding to watch a movie in the theatre or purchasing a movie on demand, viewers always check the movie’s rating, reviews from famous critiques to decide whether to proceed with the movie or not. This project implements a command line program for such a system that allows viewers to check the latest movies, its actors, ratings from other viewers and also reviews and comments from movie critiques.

# SPECIFICATION
In order to the run the application in a system, we need the MySQL Workbench and Intellij IDE to run the java application if code needs to be modified and run with the modified code. Another way to run the application is to run the JAR file in the command line and access the application. 

The above mentioned software can be downloaded from https://www.jetbrains.com/idea/download/#section=mac and
https://dev.mysql.com/downloads/workbench/

The project uses Java11 and the link to download the same or a higher version can be found in https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

If we want to run the code in Intellij, we need to import the MySQL DB connector for Java which can be found online (https://dev.mysql.com/downloads/connector/j/?os=26). This will be used to establish a connection between our client code and with our Database model. This will be imported in the libraries section in the project section.

Once this has been setup, the username and password for the database connection created using the dump provided must be updated in SQLConnector.java file in the model package which is found inside the src folder. Doing so will help in establishing the connection between the database server and our java application using the JDBC connector library plugin.

# HOW TO RUN
In order to the run the application go to MovieReview.java file and run the main function or run the Jar provided in the terminal command using the following command
				java -jar Minecraft.jar
 
# HOW TO USE
Run the provided SQL dump in MySQLWorkbench by using the import wizard. This will setup the base database structure that will be used for our application.
When the MySQLWorkbench is running run the application in the terminal and we can star using the application.

The database consists of 8 tables –
•	Users: Contains the list of users signed up for the application and their login credentials
•	User_detail: Contains the user details such as name, date of birth, phone number and user photo
•	Movies: Contains the detail of the movie such as duration, display picture url and certification
•	Actors: List of actors, photo and their gender
•	Movie_actor: Contains the list of actors that have acted in a movie
•	Reviews: Contains the reviews posted by the critiques for a movie
•	Review_like: Contains the upvotes or downvotes for a review posted by a critique
•	Movie_like: Contains the upvotes or downvotes for a movie posted by a critique

# Program Flow

Admin Role:
•	Once an admin signs into the application he/she will be provided with a menu having the list of features that are possible for the admin. These features include Movie, Users, Profile. 
o	If the Movie option is selected, the admin can view the list of movies, add movie to the list, remove movie to the list, search for a movie or filter the movies list based on a list of options or select a movie and views it features.
o	If a movie is selected to view its feature, then the details of the movie can be viewed or updated, add a review, view a review or delete a review that is posted to the movie
•	If the Users option is selected then the admin has the option to view the list of users that are using the application, remove a user from the application, approve a newly signed up user, view the user activity for a selected user.
•	In the profile section, the user can update their details such as name, photo, phone and password.

Movie Critique Role:
•	Once a movie critique signs into the application he/she will be provided with a menu having the list of features that are possible for the admin. These features include Movie, Users, Profile. 
o	If the Movie option is selected, the critique can view the list of movies, search for a movie or filter the movies list based on a list of options or select a movie and views it features.
o	If a movie is selected to view its feature, then the details of the movie can be viewed, add a review view or delete a review that has been posted by the user to the movie
•	If the Users option is selected, then the critique has the option to view the list of fellow movie critiques and their activity.
•	In the profile section, the user can update their details such as name, photo, phone and password and request for a role change to either admin or user. If requested for an admin role change then the profile verification must again be performed

Viewer Role:
•	Once a viewer signs into the application he/she will be provided with a menu having the list of features that are possible for the admin. These features include Movie and Profile. 
o	If the Movie option is selected, the viewer can view the list of movies, search for a movie or filter the movies list based on a list of options or select a movie and views it features.
o	If a movie is selected to view its feature, then the details of the movie can be viewed, view the reviews of the movie, upvote or downvote the movie or upvote or downvote a review posted by the critique
•	In the profile section, the user can update their details such as name, photo, phone and password and request for a role change to either admin or user. If requested for an admin role or movie critique change then the profile verification must again be performed

