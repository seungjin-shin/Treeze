/**
 * Unused for now.
 * 
 * @constructor
 */
<script>
mindmaps.NewDocumentView = function() {

};

/**
 * Creates a new NewDocumentPresenter. This presenter has no view associated
 * with it for now. It simply creates a new document. It could in the future
 * display a dialog where the user could chose options like document title and
 * such.
 * 
 * @constructor
 */
mindmaps.NewDocumentPresenter = function(eventBus, mindmapModel, view) {

  this.go = function() {
	  var chk = mindmaps.chk;
	  
	  if(chk == 1){
		  
		  
		  
//		  $.ajax({
//				url : "http://dewliteyez.appspot.com/mindmap?id=" + mindmaps.id,
//				//url : "http://localhost/mind/src/testFile/" + tempTitle + ".txt",
//			}).done(function(data) {
//				alert(data);
////			var doc = mindmaps.Document.fromJSON(data);
////			mindmapModel.setDocument(doc);
//			});
	  
	  }
	  else{
		  var doc = new mindmaps.Document();
		  mindmapModel.setDocument(doc);
	  }
  };
};
</script>
