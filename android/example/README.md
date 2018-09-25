Example 
```
var gsm = require('com.miga.gsm');

var win = Ti.UI.createWindow({
	backgroundColor: '#fff'
});

var lbl = Ti.UI.createLabel({
    color:"#000",
    text: ""
});
win.add(lbl);

gsm.getData({
    success: function(e){
        lbl.text = JSON.stringify(e);
    }
});
win.open();
```
