# Location Data

## Summary
- Log data of Facebook Location 
- last updated at 2020-08-15

## *File List*
1. last_location.json
2. primary_location.json
3. timezone.json

## *Data Specification(Estimated)*
- data specification of each file

### _1.last_location.json_
- log data of last(?) location

|Json Key|Data Type|Note|
|:---|:---:|:---|
|last_location|object||
||||
|time|long|unixtime(sec) of last visited time?|
||||
|coordinate|object|location object |
|latitude|double|latitude (WGS84?)|
|longitude|double|longitude (WGS84?)|

### _2.primary_location.json_
- log data of primary(?) location

|Json Key|Data Type|Note|
|:---|:---:|:---|
|primary_location|object||
||||
|city_region_pairs|array|string array of address?|
|zipcode|array|string array of zipcode|

### _3.timezone.json_
- timezone data

|Json Key|Data Type|Note|
|:---|:---:|:---|
|timezone|string|timezone string like 'Asia/Tokyo'|

