<script>
/**
 * Unused for now.
 * 
 * @constructor
 */
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
	  	<%@ page import = "java.io.*" %>
	  	<%@ page import = "java.net.*" %>
	<%
	  String id;
	  String mindmap;
	  String allmindmap;
	  String tmp;
	  BufferedReader br;
	%>
  this.go = function() {
	  var chk = mindmaps.chk;
	  
	  if(chk == 1){
	  	<%
	  	id = request.getParameter("id");
	  	mindmap = ""; 

	  	allmindmap = "http://treeze-map.appspot.com/mindmap?id="+ id;
	  	br = new BufferedReader(new InputStreamReader((new URL(allmindmap)).openConnection().getInputStream(),"UTF-8"));

	  	while((tmp = br.readLine()) != null)
	  		mindmap += tmp;
	  	%>
	  	var doc = mindmaps.Document.fromJSON('<%=mindmap%>');
	  	mindmapModel.setDocument(doc);
		  
//		  $.ajax({
//				url : "http://dewliteyez.appspot.com/mindmap?id=" + mindmaps.id,
//				//url : "http://localhost/mind/src/testFile/" + tempTitle + ".txt",
//			}).done(function(data) {
//				alert(data);
//			var doc = mindmaps.Document.fromJSON(data);
////			mindmapModel.setDocument(doc);
//			});
	  
	  }
	  else if(chk == 0){
		  var doc = new mindmaps.Document();
		  mindmapModel.setDocument(doc);
	  }
	  else{
	  	<%@ page import = "java.io.*" %>
	  	<%@ page import = "java.net.*" %>
	  	<%
	  	id = request.getParameter("id");
	  	mindmap = ""; 
	  	String user = request.getParameter("user");
	  	String version = request.getParameter("version");

	  	allmindmap = "http://treeze-map.appspot.com/feedback?id=" + id + "&user=" + user + "&version=" + version;
	  	br = new BufferedReader(new InputStreamReader((new URL(allmindmap)).openConnection().getInputStream(),"UTF-8"));

	  	while((tmp = br.readLine()) != null)
	  		mindmap += tmp;
	  	%>
	  	var doc = mindmaps.Document.fromJSON('<%=mindmap%>');
	  	mindmapModel.setDocument(doc);
	  }
  };
};
</script>
