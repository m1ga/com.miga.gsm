# Titanium GSM module for Android

Read out GSM/Sim information

## Method
```javascript
var gsm = require('com.miga.gsm');
gsm.getData({
    success: function(e){
        console.log(JSON.stringify(e));
    }
});
```

## Output

* cid
* lac
* mcc
* mnc
* operatorname
* simcountrycode
* phoneNumber*

***Notice:** the content of phoneNumber is unreliable. Sometimes phoneNumber is empty.

## Example
[app.js](https://github.com/m1ga/com.miga.gsm/blob/master/android/example/app.js)
