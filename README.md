# Offloader - Android
**_Load webview url in offline mode_**
## Need minSDK of 22 for working properly

## This library is still under development and we are happy to hear about any improvement or suggession
### Note : This version currently doesn't support prompt connection drops and will be upgraded as a future release


 ### Setup
>Step 1. Add the JitPack repository to your settings.gradle
```gradle
allprojects {
		repositories {
			
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  >Step 2. Add the dependency
  ```gradle
  dependencies {
	        implementation 'com.github.techseya:Offloader:0.0.1'
	}
  ```
  ### Usage 
 
  
  >For loading webview in offline mode
  ```java
          
	  OffLoader.ReviveView("www.techseya.lk",this,R.id.webview);
	 
	  


  ```
  
|Param number  | Parameter   |      Use Case | 
|-----|----------|:-------------:|
| 1 | url |  Defined the url needed to be loaded in webview |
|2  | context |   ActivityName.this(Defines Context)  |   
| 3 | id | id of the webview | 

   
  
  _Intellectual property of ©Techseya 2023_
  
