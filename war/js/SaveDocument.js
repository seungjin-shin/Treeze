/**
* Creates a new SaveDocumentView. This view renders a dialog where the user can
* save the mind map.
* 
* @constructor
*/
mindmaps.SaveDocumentView = function() {
  var self = this;

  var $dialog = $("#template-save").tmpl().dialog({
    autoOpen : false,
    modal : true,
    zIndex : 5000,
    width : 550,
    close : function() {
      // remove dialog from DOM
      $(this).dialog("destroy");
      $(this).remove();
    }
  });


  var $saveCloudStorageButton = $("#button-save-cloudstorage").button().click(
    function() {
      if (self.cloudStorageButtonClicked) {
        self.cloudStorageButtonClicked();
      }
    });

  var $localSorageButton = $("#button-save-localstorage").button().click(
    function() {
      if (self.localStorageButtonClicked) {
        self.localStorageButtonClicked();
      }
    });

  var $autoSaveCheckbox = $("#checkbox-autosave-localstorage").click(
    function() {
      if (self.autoSaveCheckboxClicked) {
        self.autoSaveCheckboxClicked($(this).prop("checked"));
      }
    });

  var $hddSaveButton = $("#button-save-hdd").button().downloadify({
    filename : function() {
      if (self.fileNameRequested) {
        return self.fileNameRequested();
      }
    },
    data : function() {
      if (self.fileContentsRequested) {
        return self.fileContentsRequested();
      }
    },
    onComplete : function() {
      if (self.saveToHddComplete) {
        self.saveToHddComplete();
      }
    },
    onError : function() {
      console.log("error while saving to hdd");
    },
    swf : 'media/downloadify.swf',
    downloadImage : 'img/transparent.png',
    width : 65,
    height : 29,
    append : true
  });

  this.setAutoSaveCheckboxState = function(checked) {
    $autoSaveCheckbox.prop("checked", checked);
  }

  this.showSaveDialog = function() {
    $dialog.dialog("open");
  };

  this.hideSaveDialog = function() {
    $dialog.dialog("close");
  };

  this.showCloudError = function(msg) {
    $dialog.find('.cloud-error').text(msg);
  }
};

/**
* Creates a new SaveDocumentPresenter. The presenter can store documents in the
* local storage or to a hard disk.
* 
* @constructor
* @param {mindmaps.EventBus} eventBus
* @param {mindmaps.MindMapModel} mindmapModel
* @param {mindmaps.SaveDocumentView} view
* @param {mindmaps.AutoSaveController} autosaveController
* @param {mindmaps.FilePicker} filePicker
*/
mindmaps.SaveDocumentPresenter = function(eventBus, mindmapModel, view, autosaveController, filePicker) {

  /**
  * Save in cloud button was clicked.
  */
  view.cloudStorageButtonClicked = function() {
    mindmaps.Util.trackEvent("Clicks", "cloud-save");

    filePicker.save({
      success: function() {
        view.hideSaveDialog();
      },
      error: function(msg) {
        view.showCloudError(msg);
      }
    });
  };

  /**
  * View callback when local storage button was clicked. Saves the document
  * in the local storage.
  * 
  * @ignore
  */
  view.localStorageButtonClicked = function() {
    mindmaps.Util.trackEvent("Clicks", "localstorage-save");

    var success = mindmapModel.saveToLocalStorage();
    if (success) {
      view.hideSaveDialog();
    } else {
      eventBus.publish(mindmaps.Event.NOTIFICATION_ERROR, "Error while saving to local storage");
    }
  };


  /**
  * View callback: Enables or disables the autosave function for localstorage.
  *
  * @ignore
  */
  view.autoSaveCheckboxClicked = function(checked) {
    if (checked) {
      autosaveController.enable();
    } else {
      autosaveController.disable();
    }
  }

  /**
  * View callback: Returns the filename for the document for saving on hard
  * drive.
  * 
  * @ignore
  * @returns {String}
  */
  view.fileNameRequested = function() {
    mindmaps.Util.trackEvent("Clicks", "hdd-save");

    return mindmapModel.getMindMap().getRoot().getCaption() + ".json";
  };

  /**
  * View callback: Returns the serialized document.
  * 
  * @ignore
  * @returns {String}
  */
  view.fileContentsRequested = function() {
    var doc = mindmapModel.getDocument();
    
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
     
     var url = "mindmap";
     xmlhttp.onreadystatechange = callBackTEST;
     xmlhttp.open("POST",url,true);
     xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
     xmlhttp.setRequestHeader("Content-length", params.length);
     xmlhttp.setRequestHeader("Connection", "close");
     xmlhttp.send(params);
   }
   else {
    alert("Your browser does not support XMLHTTP. so impossible to save your mindmap. please you use Chrome browser or so.");
  }
  
  return doc.prepareSave().serialize();
};

  /**
  * View callback: Saving to the hard drive was sucessful.
  * 
  * @ignore
  */
  view.saveToHddComplete = function() {
    var doc = mindmapModel.getDocument();
    eventBus.publish(mindmaps.Event.DOCUMENT_SAVED, doc);
    view.hideSaveDialog();
  };

  this.go = function() {
    view.setAutoSaveCheckboxState(autosaveController.isEnabled());
    view.showSaveDialog();
  };
  
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
