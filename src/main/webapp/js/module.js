angular.module("myApp",["angularBeans"])

//.config(function(realtimeManagerProvider){
//	
//	var options = {
//	       protocols_whitelist : ["iframe-eventsource","xdr-streaming", "xhr-polling", "xdr-polling", "iframe-htmlfile", "iframe-xhr-polling" ],
//	        debug : true
//	    };
//	
//	realtimeManagerProvider.setOptions(options);
//	realtimeManagerProvider.webSocketEnabled(false);
//	
//})

.controller("MyCtrl",MyCtrl)
;
