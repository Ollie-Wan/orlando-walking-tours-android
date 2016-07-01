# Orlando Walking Tours Android  [![Build Status](https://travis-ci.org/cforlando/orlando-walking-tours-android.svg?branch=master)](https://travis-ci.org/cforlando/orlando-walking-tours-android)
##About  
**Orlando Walking Tours** is an Android app that will allow users to create customized walking tours of the various historic locations around the city of Orlando. 

**Version** 1.0 (MVP)

**Data Source** 
The list of historic locations is currently stored [here](https://brigades.opendatanetwork.com/dataset/Orlando-Historical-Landmarks/hzkr-id6u).

**Other repositories:**  
-Orlando Walking Tours for iOS : https://github.com/cforlando/orlando-walking-tours-ios

## Installation
To be able to build and run this app would need 
- Android studio - 2.1
- Android 6.X (API 24) SDK and tools
- buildToolsVersion - 24.0.0
- Min. JDK 1.8

## Development

#### Models
New classes that would hold some kind of data should be stored in the **com.codefororlando.orlandowalkingtours.models package**. 

#### Managers
If we ever need to pull data from new data sources, classes that would be handling such requests should be stored in the **com.codefororlando.orlandowalkingtours.managers** package. 
We are using [ion](https://github.com/koush/ion) for pulling the data from the API. Look at the implentation of **HistoricLandmarkManager.java** as an example.

#### Deserializer
Any new deserializers that we need for the app should be stored in the **com.codefororlando.orlandowalkingtours.deserializer** package. For json deserializer use the **HistoricLandmarkDeserializer.java** for reference.

#### Development utilities
Any tools that would ease up the software development should be stored in the  **com.codefororlando.orlandowalkingtours.utilities** package and the DevelopmentUtilities class.

## Contributing

We encourage anyone who is interested in contributing to Orlando Walking Tours to do so!  In order to ensure good code quality, there are some guidelines we would like to adhere to when contributing to this project. 

#### Code for Orlando Members
If you are currently a member of Code for Orlando (ie. you have access to the Code for Orlando Github repo), we ask that you not push any commits directly into the master branch.  Please create a branch first.  You can do this by using the command `git checkout -b [name-of-branch]`.  You can name the branch anything you'd like, but we encourage using the following: `[your-github-username]/develop`.  When you are ready to commit, push your changes using the command 'git push origin [name-of-branch]` and then submit a pull request.  If you are working on a specific issue (which you should be! :P ), reference the issue number in the pull request.

By following these guidelines, we will be able to perform code reviews on all commits before merging with the master branch!

#### Non-Code for Orlando Members
For anyone not currently a member of Code for Orlando, we still encourage you to contribute if you are interested!  (Of course, we strongly encourage you to come to a meetup and meet the rest of us!)  If you would like to contribute, go ahead and fork the repo.  Make your changes and push them to your forked repo.  Then you can submit a pull request on Github.

## License
The MIT License (MIT)
[LICENSE](https://github.com/cforlando/orlando-walking-tours-android/blob/master/LICENSE)

## About Code for Orlando

Code for Orlando, a local Code for America brigade, brings the community together to improve Orlando through technology. We are a group of “civic hackers” from various disciplines who are committed to volunteering our talents to make a difference in the local community through technology. We unite to improve the way the community, visitors, and local government experience Orlando.
