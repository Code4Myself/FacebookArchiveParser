# Security & Login Data

## Summary
- Log data of Facebook login and security in Json format
    - login/logout history 
    - activity history in active time period on Facebook
    - device information access to Facebook 
- last updated at 2020-08-12

## *File List*
1. account_activity.json
2. administrative_records.json
3. authorized_logins.json
4. contact_verifications.json
5. datr_cookie_info.json
6. login_protection_data.json
7. logins_and_logouts.json
8. mobile_devices.json
9. used_ip_addresses.json
10. where_you're_logged_in.json
11. your_facebook_activity.json

## *Data Specification(Estimated)*
- data specification of each file

### _1.account_activity.json_
- log data of account activities such as session start

|Json Key|Data Type|Note|
|:---|:---:|:---|
|account_activity|array|array of account activity objects|
||||
|action|string|action title such as login, logout, and session updated|
|timestamp|long|timestamp unixtime(sec)|
|ip_address|string|IP address v4 or v6|
|user_agent|string|user agent|
|datr_cookie|string|masked DATR cookie ID|
|city|string|accessed city name|
|region|string|accessed region|
|country|string|accessed country code|
|site_name|string|accessed domain|

### _2.administrative_records.json_
- log data of administration activities such as change password

|Json Key|Data Type|Note|
|:---|:---:|:---|
|admin_records|array|array of administrative activity objects|
||||
|event|string|event title such as password change and checkpoint|
||||
|session|object||
|created_timestamp|long|created timestamp in unixtime(ssec)|
|ip_address|string|accessed IP address v4 or v6, sometimes missing|
|user_agent|string|user agent|
|datr_cookie|string|maskedDATR cookie ID, sometimes empty|
||||
|extra_info|object||
|old_name|string|old name|
|new_name|string|new name|
|old_number|string|old phone number|
|new_number|string|new phone number|
|old_vanity|string|old vanity|
|new_vanity|string|new vanity|
|old_email|string|old email|
|new_email|string|new email|

### _3.authorized_login.json_
- log data of authorized login with recognized devices

|Json Key|Data Type|Note|
|:---|:---:|:---|
|recognized_devices|array|object array|
||||
|name|string|app name?|
|created_timestamp|long|unixtime(sec) of created time|
|updated_timestamp|long|unixtime(sec) of udpated time|
|ip_address|string|IP address. v4 or v6?|
|user_agent|string|user agent|
|datr_cookie|string|empty?|


### _4.contact_verifications.json_
- log data of verification of your account?

|Json Key|Data Type|Note|
|:---|:---:|:---|
|contact_verifications|array|object array|
||||
|contact|string|email address to verify?|
|contact_type|int|???|
|verification_time|long|unixtime(sec) of verification|


### _5.datr_cookie_info.json_
- ??? (need help to fill the explanation)

|Json Key|Data Type|Note|
|:---|:---:|:---|
|datr_stats|object||
||||
|???(datr_cookie?)|array|unixtime(sec) array ?|


### _6.login_protection_data.json_
- ??? (need help to fill the explanation)

|Json Key|Data Type|Note|
|:---|:---:|:---|
|login_protection_data|array|object array|
||||
|name|string|activity name?|
||||
|session|object||
|created_timestamp|long|unixtime(sec) of created time|
|updated_timestamp|long|unixtime(sec) of updated time|
|ip_address|string|IP address v4 or v6|


### _7.logins_and_logouts.json_
- log data of login and logout ? 

|Json Key|Data Type|Note|
|:---|:---:|:---|
|account_accesses|array|object array|
||||
|action|string|action name?|
|timestamp|long|unixtime(sec) of action happened|
|site|string|site|
|ip_address|string|IP address accessed from, v4 or v6|


### _8.mobile_devices.json_
- device info? 

|Json Key|Data Type|Note|
|:---|:---:|:---|
|devices|array|object array|
||||
|type|string|device type|
|os|string|os version|
|update_time|long|unixtime(sec) of update time|
|advertiser_id|string|adid|
|udid|string|udid|
|redact_tokens|array|string array of ???|
|push_tokens|array|object array|
|family_device_id|string|????|
||||
|disabled|boolean|flag of ???|
|client_update_time|long|unixtime(sec) of update time?|
|creation_time|long|unixtime(sec) of created time?|
|app_version|string|app version|
|locale|string|locale like ja_JP|
|os_version|string|OS version|
|token|string|???|
|device_id|string|device ID? IMEI?|


### _9.used_ip_addresses.json_
- IP address list 

|Json Key|Data Type|Note|
|:---|:---:|:---|
|used_ip_address|array||
||||
|ip|string|IP address, v4 or v6|
|action|string|action name|
|timestamp|long|unixtime(sec) of action happened|


### _10.where_you're_logged_in.json_
- log data of access location

|Json Key|Data Type|Note|
|:---|:---:|:---|
|active_sessions|array||
||||
|created_timestamp|long|unixtime(sec) of created time|
|updated_timestamp|long|unixtime(sec) of updated time|
|ip_address|string|IP address logged in, v4 or v6|
|user_agent|string|user agent|
|datr_cookie|string|DATR COOKIE|
|device|string|device name|
|location|string|location name|
|app|string|application name such as chrome or messenger|


### _11.your_facebook_activity.json_
- ??? (need help to fill the explanation)

|Json Key|Data Type|Note|
|:---|:---:|:---|
|last_activity|object|?|
||||
|last_activity_time|object|?|
||||
|???|object||
|activity_by_day|array|unixtime array of ?|
||||
|???|object||
|activity_by_day|array|unixtime array of ?|

