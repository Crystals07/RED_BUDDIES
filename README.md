 #  RED_BUDDIES
 
 This is a simple read me file for my project 

 * RED_BUDDIES is a simple blood donation application that have fast search mechanism

 * Helps to find nearby donar 

 * This is an ongoing project build to overcome blood donation problem

 * It provides a new approach of voluntary blood donation using technical platforms 

 # Technology Used

* Android

* Java

* Firebase

 # Pros

* Real time database is used

* Secure Authentication (  Only few are yet to be added )

* Open source project 

* Attractive and simple UI

* Light weight application (less than ~ 3 mb) apk volume  

* Also generates crash reports and easy to moniter authenticated emails

 #  Database 

 Firebase is used for authentication,Real time database and for crash reports generation

 Data is arranged like json format.Each user is categorized under Doner and Registered

 1. Registered contains users that have only registered but not are donars

 2. Donars contains users which are both registered and donars

 Donars have additional parameters like city and blood group

 Each user is categorized in Donar under city and then blood group hierarchically

 So query does not need to check every donar user blood group rather than it check only 

 For particular blood group in particular city filtering out many users thats not the only

 Reason for it's fast search.If donar does not exists for particular blood group in particular city

 It just check first whether that city have any registered donar of particular b-group rather than searching

 Entire donar list.

 