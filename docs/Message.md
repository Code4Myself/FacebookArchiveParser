# Summary
- log data of Facebook Messenger in Json format
- last updated at 2020-08-12

# Message Data Specification(Estimated)
1. **title** (String): title 
2. **thread_type** (String): _Regular_
3. **thread_path** (String): Unique ID of message thread in a certain person.
4. **status** (String): _Pending_
5. **is_still_participant** (boolean): ??? 
6. **participants** (array): array of participant names (string)
7. **messages** (array): array of message objects(message object)  
   1. **sender_name** (String): sender name
   2. **timestamp** (int/long): time stamp
   3. **content** (String): message body. sometimes missing when sharing media contents.
   4. **type** (String): _Generic_, _Share_, _Call_
   5. **photos** (array): [option]array of photo file URIs in a relative path from message file directory
   6. **files** (array): [option]array of shared file URIs in a relative path from message file directory
   7. **audio_files** (array): [option]array of audio file URIs in a relative path from message file directory
   8. **videos** (array): [option]array of video URIs and thumbnail URIs in a relative path from message file directory
   9. **share** (object): [option]shared URI object
   10. **sticker** (object): [option]posted sticker URI object
   11. **reactions** (array): [option]array of reaction object(reaction and actor)
   12. **call_duration** (int): [option]voice call duration through messenger. 
 