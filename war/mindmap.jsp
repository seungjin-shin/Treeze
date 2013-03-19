<!doctype html>
<!-- DEBUG -->
<!-- disable offline caching in debug mode -->
<html>
<!-- /DEBUG -->
<!-- PRODUCTION
<html manifest="cache.appcache">
/PRODUCTION -->
<head>
<meta charset="utf-8">
<meta name="description" content="mindmaps is an HTML5 based mind mapping app. It lets you create neat looking mind maps in the browser." />
<meta name="keywords" content="mind maps html5 mindmaps offline easy intuitive" />
<meta name="google" content="notranslate"/>
<title>mindmaps</title>
<link rel="icon" type="image/png" href="img/favicon.png" />
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/app.css">
<link rel="stylesheet" href="css/Aristo/jquery-ui-1.8.7.custom.css" />
<link rel="stylesheet" href="css/minicolors/jquery.miniColors.css">

<script id="template-float-panel" type="text/x-jquery-tmpl">
<div class="ui-widget ui-dialog ui-corner-all ui-widget-content float-panel no-select">
  <div class="ui-dialog-titlebar ui-widget-header ui-helper-clearfix">
    <span class="ui-dialog-title">${title}</span>
    <a class="ui-dialog-titlebar-close ui-corner-all" href="#" role="button">
      <span class="ui-icon"></span>
    </a>
  </div>
  <div class="ui-dialog-content ui-widget-content">
  </div>
</div>
</script>

<script id="template-notification" type="text/x-jquery-tmpl">
<div class="notification">
  {{if closeButton}}
  <a href="#" class="close-button">x</a>
  {{/if}}
  {{if title}}
  <h1 class="title">{{html title}}</h1>
  {{/if}}
  <div class="content">{{html content}}</div>
</div>
</script>

<script id="template-open-table-item" type="text/x-jquery-tmpl">
<tr>
  <td><a class="title" href="#">${title}</a></td>
  <td><a class="delete" href="#">delete</a></td>
</tr>
</script>

<script id="template-open" type="text/x-jquery-tmpl">
<div id="open-dialog" class="file-dialog" title="Open mind map">
  <h1><span class="highlight">New!</span> From the Cloud: Dropbox and more</h1>
  <p>Open, save and share your mind maps online in your favorite cloud storage. Supports Google Drive, Dropbox and more! Powered by <a href="http://www.filepicker.io" target="_blank">filepicker.io</a>.</p>
  <button id="button-open-cloud">Open</button>
  <span class="cloud-loading">Loading...</span>
  <span class="cloud-error error"></span>
  <div class="seperator"></div>
  <h1>Local Storage</h1>
  <p>This is a list of all mind maps that are saved in your browser's local storage. Click on the title of a map to open it.</p>
  <table class="localstorage-filelist">
    <thead>
      <tr>
        <th class="title">Title</th>
        <th class="modified">Last Modified</th>
        <th class="delete"></th>
      </tr>
    </thead>
    <tbody class="document-list"></tbody>
  </table>
  <div class="seperator"></div>
  <h1>From file</h1>
  <p>Choose a mind map from your computer's hard drive.</p>
  <div class="file-chooser">
    <input type="file" />
  </div>
</div>
</script>

<script id="template-save" type="text/x-jquery-tmpl">
<div id="save-dialog" class="file-dialog" title="Save mind map">
  <h1><span class="highlight">New!</span> In the Cloud: Dropbox and more</h1>
  <p>Open, save and share your mind maps online in your favorite cloud storage. Supports Google Drive, Dropbox and more! Powered by <a href="http://www.filepicker.io" target="_blank">filepicker.io</a>.</p>
  <button id="button-save-cloudstorage">Save</button>
  <span class="cloud-error error"></span>
  <div class="seperator"></div>
  <h1>Local Storage</h1>
  <p>You can save your mind map in your browser's local storage. Be aware that this is still experimental: the space is limited and there is no guarantee that the browser will keep this document forever. Useful for frequent backups in combination with cloud storage.</p>
  <button id="button-save-localstorage">Save</button>
  <input type="checkbox" class="autosave" id="checkbox-autosave-localstorage">
  <label for="checkbox-autosave-localstorage">Save automatically every minute.</label>
  <div class="seperator"></div>
  <h1>To file</h1>
  <p>Save the mind map as a file on your computer.</p>
  <div id="button-save-hdd">Save</div>
</div>
</script>

<script id="template-navigator" type="text/x-jquery-tmpl">
<div id="navigator">
  <div class="active">
    <div id="navi-content">
      <div id="navi-canvas-wrapper">
        <canvas id="navi-canvas"></canvas>
        <div id="navi-canvas-overlay"></div>
      </div>
      <div id="navi-controls">
        <span id="navi-zoom-level"></span>
        <div class="button-zoom" id="button-navi-zoom-out"></div>
        <div id="navi-slider"></div>
        <div class="button-zoom" id="button-navi-zoom-in"></div>
      </div>
    </div>
  </div>
  <div class="inactive">
  </div>
</div>
</script>


<script id="template-inspector" type="text/x-jquery-tmpl">
<div id="inspector">
  <div id="inspector-content">
    <table id="inspector-table">
      <tr>
        <td>Font size:</td>
        <td><div
            class="buttonset buttons-very-small buttons-less-padding">
            <button id="inspector-button-font-size-decrease">A-</button>
            <button id="inspector-button-font-size-increase">A+</button>
          </div></td>
      </tr>
      <tr>
        <td>Font style:</td>
        <td><div
            class="font-styles buttonset buttons-very-small buttons-less-padding">
            <input type="checkbox" id="inspector-checkbox-font-bold" /> 
            <label
            for="inspector-checkbox-font-bold" id="inspector-label-font-bold">B</label>
              
            <input type="checkbox" id="inspector-checkbox-font-italic" /> 
            <label
            for="inspector-checkbox-font-italic" id="inspector-label-font-italic">I</label> 
            
            <input
            type="checkbox" id="inspector-checkbox-font-underline" /> 
            <label
            for="inspector-checkbox-font-underline" id="inspector-label-font-underline">U</label> 
            
            <input
            type="checkbox" id="inspector-checkbox-font-linethrough" />
             <label
            for="inspector-checkbox-font-linethrough" id="inspector-label-font-linethrough">S</label>
          </div>
        </td>
      </tr>
      <tr>
        <td>Font color:</td>
        <td><input type="hidden" id="inspector-font-color-picker"
          class="colorpicker" /></td>
      </tr>
      <tr>
        <td>Branch color:</td>
        <td><input type="hidden" id="inspector-branch-color-picker"
          class="colorpicker" />
          <button id="inspector-button-branch-color-children" title="Apply branch color to all children" class="right buttons-small buttons-less-padding">Inherit</button>
        </td>
      </tr>
    </table>
  </div>
</div>
</script>

<script id="template-export-map" type="text/x-jquery-tmpl">
<div id="export-map-dialog" title="Export mind map">
  <h2 class='image-description'>To download the map right-click the
    image and select "Save Image As"</h2>
  <div id="export-preview"></div>
</div>
</script>

</head>
<body>
  <div id="print-area">
    <p class="print-placeholder">Please use the print option from the
      mind map menu</p>
  </div>
  <!-- DEBUG -->
  <div id="debug-warning">Running in DEBUG mode.</div>
  <!-- /DEBUG -->
  <div id="container">
    <div id="topbar">
      <div id="toolbar">
        <div id="logo" class="logo-bg">
          <span>mindmaps</span>
        </div>

        <div class="buttons">
          <span class="buttons-left"> </span> <span class="buttons-right">
          </span>
        </div>

      </div>
    </div>
    <div id="canvas-container">
      <div id="drawing-area" class="no-select"></div>
    </div>
    <div id="bottombar">
      <div id="about">
        <a href="about.html" target="_blank">About mindmaps</a> <span
          style="padding: 0 4px;">|</span> <a style="font-weight: bold"
          href="https://spreadsheets.google.com/a/drichard.org/spreadsheet/viewform?formkey=dEE3VzFWOFp6ZV9odEhhczVBUUdzc2c6MQ"
          target="_blank">Feedback</a>
      </div>
      <div id="statusbar">
        <div
          class="buttons buttons-right buttons-small buttons-less-padding"></div>
      </div>
    </div>
  </div>

  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
  <script src="//api.filepicker.io/v0/filepicker.js"></script>


	<%
	String id = request.getParameter("id");
    String chk = request.getParameter("chk");
    %>

  <!-- DEBUG -->
  <!-- set debug flag for all scripts. Will be removed in production -->
  <script type="text/javascript">
    var mindmaps = mindmaps || {};
    mindmaps.DEBUG = true;
    //mindmaps.id = ""+"<%=id%>";
    mindmaps.chk = <%=chk%>;
  </script>
  <!-- /DEBUG -->

  <!-- JS:LIB:BEGIN -->
  <script src="js/libs/jquery-ui-1.8.11.custom.min.js"></script>
  <script src="js/libs/dragscrollable.js"></script>
  <script src="js/libs/jquery.hotkeys.js"></script>
  <script src="js/libs/jquery.mousewheel.js"></script>
  <script src="js/libs/jquery.minicolors.js"></script>
  <script src="js/libs/jquery.tmpl.js"></script>
  <script src="js/libs/swfobject.js"></script>
  <script src="js/libs/downloadify.min.js"></script>
  <script src="js/libs/events.js"></script>

  <script src="js/MindMaps.js"></script>
  <script src="js/Command.js"></script>
  <script src="js/CommandRegistry.js"></script>
  <script src="js/Action.js"></script>
  <script src="js/Util.js"></script>
  <script src="js/Point.js"></script>
  <script src="js/Document.js"></script>
  <script src="js/MindMap.js"></script>
  <script src="js/Node.js"></script>
  <script src="js/NodeMap.js"></script>
  <script src="js/UndoManager.js"></script>
  <script src="js/UndoController.js"></script>
  <script src="js/ClipboardController.js"></script>
  <script src="js/ZoomController.js"></script>
  <script src="js/ShortcutController.js"></script>
  <script src="js/HelpController.js"></script>
  <script src="js/FloatPanel.js"></script>
  <script src="js/Navigator.js"></script>
  <script src="js/Inspector.js"></script>
  <script src="js/ToolBar.js"></script>
  <script src="js/StatusBar.js"></script>
  <script src="js/CanvasDrawingTools.js"></script>
  <script src="js/CanvasView.js"></script>
  <script src="js/CanvasPresenter.js"></script>
  <script src="js/ApplicationController.js"></script>
  <script src="js/MindMapModel.js"></script>
  
  <!--<script src="js/NewDocument.js"></script>-->
  <jsp:include page="js/NewDocument.jsp">
  	<jsp:param name="id" value="<%=id%>" />
  </jsp:include>
  
  <script src="js/OpenDocument.js"></script>
  
  <script src="js/SaveDocument.js"></script>
  
  <script src="js/MainViewController.js"></script>
  <script src="js/Storage.js"></script>
  <script src="js/Event.js"></script>
  <script src="js/Notification.js"></script>
  <script src="js/StaticCanvas.js"></script>
  <script src="js/PrintController.js"></script>
  <script src="js/ExportMap.js"></script>
  <script src="js/AutoSaveController.js"></script>
  <script src="js/FilePicker.js"></script>
  <!-- JS:LIB:END -->

<script>
   $(function() {

    //var request = new XMLHttpRequest();
    //request.onreadystatechange = function() {
    //if (request.readyState == 4 && request.status == 200) {
    //alert(request.responseText);
    //}
    //}
    //request.open("GET", "http://dewliteyez.appspot.com/mindmap?id=1a03c923-279d-4576-9d90-3898fdbfb951");
    //request.send();
    //alert(request.responseText);
    // var temp;
    // $.ajax({
    // url : "http://localhost/mind/src/test.txt",
    // }).done(function(data) {
    // temp = data;
    // alert(data);
    // });
    //var doc = mindmaps.Document.fromJSON(temp);
    //mindmapModel.setDocument(doc);
    // open ������ ��� dialog �ݱ�
    //var doc = mindmaps.Document.fromJSON(temp);
    //alert(temp);
    //mindmapModel.setDocument(doc);
   });
   
  </script>


  <!-- PRODUCTION
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push([ '_setAccount', 'UA-23624804-1' ]);
  _gaq.push([ '_trackPageview' ]);
  (function() {
    var ga = document.createElement('script');
    ga.type = 'text/javascript';
    ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl'
        : 'http://www')
        + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(ga, s);
  })();
</script>
/PRODUCTION -->
</body>
</html>
