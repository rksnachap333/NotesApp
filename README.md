Project Detail 
---------------
* On adding Notes - 
  (1.) It will add note first to Room Db and then check for internet and sync to the server.
  (2.) There is a work manager that start runing and it has task to check for any pending Notes that are not synced. If there is and also internet connection then it send Notes to the mock backend.
  (3.) If there is no internet then it waits for connection till then note status will be show as not synced and once connected with internet it does the syncing.

  On Delete Notes -
    (1.) It delete the notes and mark the status of notes as deleted and pending delete true and synck is called.
    (2) Again work manager comes in the picture check for any pending delete does its works if internet available clear it from local db.
      
