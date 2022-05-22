# BrandynMumma2205

# Week 2 Updates
## Installation
- Drag app-debug.apk onto emulator device and should install to device

## Hardware Requirements
- Emulator used for testing: Pixel 3 API 29

## Login Requirements/ Credentials for testing
- No logins setup so far, due to early technical issues putting me behind schedule

## Known bugs
- N/A

## Special Requirements
- Without login credentials for testing purposes, the current pages built should be able to notifiy when edit text fields aren;t filled in correctly. If an alert doesn't show, then all fields should be filled in. If all fields aren't filled in, an alert should pop up telling the user what was wrong and how to fix it. 


# Week 3 Updates
## Installation
- Drag app-debug.apk onto emulator device and should install to device

## Hardware Requirements
- Emulator used for testing: Pixel 3 API 29

## Login Requirements/ Credentials for testing
- Room Code: 0000-0000-0000-0000
- Username: JDoe
- Password: 1111

You may also try and create your own test room if you like, just know it will be deleted by the end of next week

## Known bugs
- I'm seeing a lot of time it seems to say there are duplicate files, but after running "clean build" it fixes itself and runs the emulator
## Special Requirements
- There is no button yet for logging out of the app, but there is code to prevent the app from going backwards once inside the room. If testing a second room, make sure and exit out of the app before trying a different test run. Not all buttons show alerts right now: Add Post shows an alert since no pets are available in any rooms created or may be created for testing, settings won't show anything except in logcat, and pets list (the Paw Button) should go to the pets list but won't have anything major done to layout yet besides the title being different.
