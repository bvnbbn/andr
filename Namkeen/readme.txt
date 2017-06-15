First of all thanks for giving this assignment to me I have really learned a lot of things.

App contains six Activities and one POJO class.

1)First I will discuss about POJO(Plain Old Java Oject) class.There is a package named model inside that package there is a class called User in which I am declaring all the things which user will enter and these all things  of a particular user will be saved in the Firebase Database who will register in the app.

Now I will come to activities 

1). SignUpActivity

I this class I am providing the code through which the User will be able to register in the app and after that all the details which the user has entered will be saved in the Firebase Database.


Firstly I have created the instance of the Firebase Database in this class.

This class has following methods created by me besides Overring all the predefined methods 

registerUser()-In this method all the data which is being entered by user is fetched and after that input validation is done to some extent of all fields and after I am calling Firebase method of createUserWithEmailAndPassword which is autheticating the user with Email and if the authentication is successful then other method CreateUser is called which I will explain later and if authentication is not successful then a message will be displayed in the toast about the problem.


CreateUser()-In this method all the user details will be entered into the Firebase Database.

addUserChangeListener()- In this method we can could have fetch the data from the database of a particular user.I am mainly setting all the fields null again.
For this activity xml file is activity_create_account



2).LoginActivity
In this activity I am authenticating if the user is already registered then the user will be able to enter the app.

button click events are also taken care of in thi sactivity as there is Forget Password button and Login Button 
on clicking Forget Password Button you will be taken to Forget Password screen 
on clicking Login Button User will be Authenticated and input validation will also be done.

3)ResetPasswordActivity
As the name of the activity explains itself in this activity I have made the user enter his email id the a link will be send to the user email id 
if the authentication of the email is successful then the message wil be displayed that link has been sent and if not succesful then  error message will be displayed accordingly. There is also a back button which will take you back to the login screen in case you remembered your password.

Till here our 1st part of the assignment is complete and now starts 2nd Part that is google sign in

4).EmailLoginAccountActivity
User will be taken to this activity when he has successfully registered and if user user is trying to Login again after clicking the Logout Button which is there in this activity xml file.
By clicking on LogOut  button User will be taken back to the Login Activity


5).GoogleSignActivity

I this activity also first of all database instance is Created then the Google sign in options object is made  and after that GoogleAPIClient object is made which is for the user who will attempt to sign in after that the prdefined methods onConnectionFailed is called in which a message will be displayed if User is not successful in sign in and message will be displayed according to the error.

Then the GoogleSignIn buuton click listener will be active and it will transfer the control to the sign in method 
In signIn() method the result of the authentication will be transfered to the onActivityResult.

In onActivityResultMethod authentication will be done and request code wil be checked and if it is successful the result will go to FirebaseAuthWith Google function and in this method again authentication will be done and if the authentication is successful then result will go to handle FirebaseAuthResult Function and in this Function all the details of the google user like display name and image url will be stored in the Firebase database.

6).Account Activity 
This is the activity which user will  be taken to if the authentication is successful with google sign method. In this activity I have implented LogOut Button also which will help the user to Logout of the app.I have implemented this feature with the help of the FireBaseAuth.AuthStateListener.


Some important things to note- In manifest File internet permission is neccesary and declaring all the activities in the manifest file is necessary.

 

