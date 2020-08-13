# Message Data

## Summary
- log data of Facebook Messenger in Json format
- last updated at 2020-08-12

## Data Specification(Estimated)
|Json Key|Data Type|Note|
|:---|:---:|:---|
|title|string|thread title|
|thread_type|string|???|
|thread_path|string|Unique ID of message thread in a certain person|
|status|string|???|
|is_still_participant|boolean|flag of participation?|
|participants|array|array of participant name(string)|
|messages|array|array of message objects(message object|
||||
|sender_name|string|name of message sender|
|timestamp|long|time stamp unixtime(sec)|
|content|string|message body. sometimes missing when sharing media contents|
|type|string|_Generic_, _Share_, _Call_|
|photos|array|array of photo file URIs in a relative path from message file directory|
|files|array|array of shared file URIs in a relative path from message file directory|
|audio_files|array|array of audio file URIs in a relative path from message file directory|
|videos|array|array of video file URIs in a relative path from message file directory|
|share|object|shared URI object|
|sticker|object|posted sticker URI|
|reactions|array|array of reaction object(reaction and actor)|
|call_duration|int|voice call duration through messenger|
