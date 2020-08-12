# Summary
- Log data of Facebook login and security in Json format
    - login/logout history 
    - activity history in active time period on Facebook
    - device information access to Facebook 
- last updated at 2020-08-12

# Security & Login Data Specification(Estimated) 

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
|Json Key|Data Type|Note|
|:---|:---:|:---|
|account_activity|array|array of account activity objects|
||||
|action|string|action title such as login, logout, and session updated|
|timestamp|long|timestamp unixtime(sec)|
|ip_address|string|IP address v4 or v6|
|u ser_agent|string|user agent|
|datr_cookie|string|masked DATR cookie ID|
|city|string|accessed city name|
|region|string|accessed region|
|country|string|accessed country code|
|site_name|string|accessed domain|

### _2.administrative_records.json_
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


_TBA_

