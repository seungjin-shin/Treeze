
mindmaps.AcceptFeedback = function(mindmapModel) {

	this.go = function(){
		var doc = mindmapModel.getDocument();

		//alert(doc.prepareSave().serialize());
		
		xmlhttp=null;
		if (window.XMLHttpRequest) {// code for all new browsers
			xmlhttp = new XMLHttpRequest();
		}
    	else if (window.ActiveXObject) {// code for IE5 and IE6
    		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    	}
    	if (xmlhttp != null) {

    		var con = doc.prepareSave().serialize();

    		var len = String(con).length;

    		var index = len / 500;
    		var params = "";

    		for(i = 0; i < index ; i++){
    			var tmp = con.substring(500 * i , 500 * (i+1));
    			params += "mindmap" + i + "=" + tmp + "&";
    		}
    		params += "cnt=" + i;
    		params += "&id=" + doc.id;
    		params += "&title=" + doc.title;

    		var url = "feedbackversion";
    		xmlhttp.onreadystatechange = callBackTEST;
    		xmlhttp.open("POST",url,true);
    		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    		xmlhttp.setRequestHeader("Content-length", params.length);
    		xmlhttp.setRequestHeader("Connection", "close");
    		xmlhttp.send(params);
    	}
    	else {
    		alert("Your browser does not support XMLHTTP. so impossible to send your mindmap. please you use Chrome browser or so.");
    	}
    }
    function callBackTEST() {
	  if (xmlhttp.readyState==4) {// 4 = "loaded"
	    if (xmlhttp.status==200) {// 200 = OK
	      //alert(xmlhttp.responseText);
	      alert(xmlhttp.responseText);
	    }
	    else {
       alert("Problem retrieving XML data");
     }
   }
 }
};
