
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Transition - vCard Theme</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- Le styles -->
	<link href="${pageContext.request.contextPath}/resources/startAssets/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/startAssets/css/component.css" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/resources/startAssets/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/startAssets/css/signin.css" rel="stylesheet">
	
	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/startAssets/js/modernizr.custom.js"></script>

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
      <![endif]-->

      <!-- Le fav and touch icons -->
      <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/startAssets/images/favicon.ico">
      <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/resources/startAssets/images/apple-touch-icon-144-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/resources/startAssets/images/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/resources/startAssets/images/apple-touch-icon-72-precomposed.png">
      <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/resources/startAssets/images/apple-touch-icon-57-precomposed.png">

      <style type="text/css">

      	.container1 {
      		padding-right: 15px;
      		padding-left: 15px;
      		margin-right: auto;
      		margin-left: auto;
      	}
      </style>

  </head>

  <body>


  	<div class="container">

  		<div id="bl-main" class="bl-main">

  			<!--=========== ABOUT SECTION ===========-->
  			<section>
  				<div class="bl-box">
  					<h2 >What is Treeze ? </h2> <!-- class="bl-icon bl-icon-about" -->
  				</div><!-- /bl-box -->
  				<div class="bl-content">
  					<div class="row-fluid">
  						<div class="span12">
  							<h2>A Little About Me</h2>
  							<p>I am a freelance web developer and web designer living in London, UK. I started freelancing in 2009 and have worked for a wide range of personal clients and agencies.</p>
  							<p>I offer a complete web solution to my clients. I can guide you through the early stages of project planning and research, help you with information architecture and user experience, design your website, and finally build the site for you.</p>
  							<p></p>
  							<p></p>
  							<p></p>
  							<p></p>
  							<div class="row-fluid">
  								<div class="span8">
  									<h2>My Skills</h2>
  									<!-- Progress Bar --> 
  									<div class="progress">
  										<div class="bar" style="width: 73%;">HTML/CSS 73%</div>
  									</div>  
  									<div class="progress">
  										<div class="bar" style="width: 100%;">Web Design 100%</div>
  									</div>  
  									<div class="progress">
  										<div class="bar" style="width: 78%;">Photography 78%</div>
  									</div>  
  									<div class="progress">
  										<div class="bar" style="width: 80%;">Sound Design 80%</div>
  									</div>  
  									<div class="progress">
  										<div class="bar" style="width: 63%;">Party 63%</div>
  									</div> 
  									<!-- Progress Bar End --> 
  								</div><!-- /span6 -->
  								<div class="span4"></div>
  								<div class="span12">
  									<br>
  									<br>
  								</div>
  								<div class="span12">
  									<div class="row about-us">
  										<div class="span3">
  											<h3><i class="icon-user icon-white"></i> More About Me?</h3>
  											Claritas est etiam processus dynamicus, and mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc. Parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima.
  										</div>
  										<div class="span3">
  											<h3><i class="icon-heart icon-white"></i> What I Love</h3>
  											Claritas est etiam processus dynamicus, and mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc. Parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima.
  										</div>
  										<div class="span3">
  											<h3><i class="icon-fire icon-white"></i> Work Hard</h3>
  											Claritas est etiam processus dynamicus, and mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc. Parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima.
  										</div>   
  										<div class="span3">
  											<h3><i class="icon-plane icon-white"></i> Ready To Go</h3>
  											Claritas est etiam processus dynamicus, and mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc. Parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima.
  										</div>   
  									</div><!-- /row-about-us -->  
  								</div><!-- /span12 -->
  							</div><!-- /row -->
  						</div><!-- /span12 -->
  					</div><!-- /row-fluid -->

  				</div><!-- /bl-content -->
  				<span class="bl-icon bl-icon-close"></span>
  			</section>



  			<!--=========== PORTFOLIO SECTION ===========-->
  			<section id="bl-work-section">
  				<div class="bl-box">
  					<h2 >Start Treeze</h2> <!-- class="bl-icon bl-icon-works"-->
  				</div>
  				<div class="bl-content">
  					<!-- Sign in -->

  					<div class="container1">
<!--  
  						<form class="form-signin"  action="signin" method="POST">
  							<h2 class="form-signin-heading">Please sign in</h2>


  							<input type="text" class="form-control" style="width:100%;"placeholder="email" autofocus>
  							<input type="password" class="form-control" style="width:100%;" placeholder="password">
  							<label class="checkbox">
  								<input type="checkbox" value="remember-me"> Remember me
  							</label>
  							<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  
  							<!-- Button to trigger modal -->
  							<!--<button href="#myModal" class="btn btn-lg btn-default btn-block" data-toggle="modal">Sign up</button>
  							<!-- Modal -->
  							<!--<div id="myModal" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  								<div class="modal-header">
  									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
  									<h3 id="myModalLabel">Modal header</h3>
  								</div>
  								<div class="modal-body">
  									<p>One fine body…</p>
  								</div>
  								<div class="modal-footer">
  									<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  									<button class="btn btn-primary">Save changes</button>
  								</div>
  							</div>



  						</form>
 -->
 
   <form class="form-signin" action="treezing/signin" method="POST">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="input-block-level" placeholder="Email address" name="email">
        <input type="password" class="input-block-level" placeholder="Password" name="password">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button href="treezing/studentTreeze"class="btn btn-large btn-primary" type="submit" >Sign in</button>
      </form>

  					</div> <!-- /container -->
  				</div><!-- /bl-content -->
  				<span class="bl-icon bl-icon-close"></span>
  			</section>



  			<!--=========== BLOG SECTION ===========-->
  			<section>
  				<div class="bl-box">
  					<h2>Treeze Manager</h2>
  				</div>
  				<div class="bl-content">
  					<div class="row-fluid">
  						<div class="span12">
  							<h2>Latest Blog Entry</h2>
  							<article>
  								<h3>Expanding Your Horizons</h3>
  								<img src="${pageContext.request.contextPath}/resources/startAssets/images/blogentry.jpg" alt="">
  								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
  								<p>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<a href="#">Read more</a></p>
  							</article>
  							<hr>
  							<h2>Other Entries</h2>
  							<article>
  								<h3>Blog Post 1</h3>
  								<p>Contrary to popular belief, Lorem Ipsum is not simply random text.... <a href="#">Read more</a></p>
  							</article>
  							<article>
  								<h3>Blog Post 2</h3>
  								<p>Contrary to popular belief, Lorem Ipsum is not simply random text.... <a href="#">Read more</a></p>
  							</article>
  							<article>
  								<h3>Blog Post 3</h3>
  								<p>Contrary to popular belief, Lorem Ipsum is not simply random text.... <a href="#">Read more</a></p>
  							</article>

  						</div><!-- /span12 -->
  					</div><!-- /row-fluid -->
  				</div><!-- /bl-content -->
  				<span class="bl-icon bl-icon-close"></span>
  			</section>



  			<!--=========== CONTACT SECTION ===========-->
  			<section>
  				<div class="bl-box">
  					<h2 >Open Treeze Education</h2>
  				</div>
  				<div class="bl-content">
  					<div class="row-fluid">
  						<div class="span12">
  							<h2>Get in touch</h2>
  							<br>
  							<p>I am available for freelance jobs. Please contact me and send me your questions and inquiries.</p>								

  							<p>
  								<p>your@email.com | +34 600669933 | Madrid, Spain</p>
  							</p>

  							<p>
  								<a href="#" class="social-network facebook"></a>
  								<a href="#" class="social-network pinterest"></a>
  								<a href="#" class="social-network flickr"></a>
  								<a href="#" class="social-network dribbble"></a>
  								<a href="#" class="social-network twitter"></a>
  								<a href="#" class="social-network apple"></a>
  								<a href="#" class="social-network skype"></a>
  								<a href="#" class="social-network tumblr"></a>
  								<a href="#" class="social-network vimeo"></a>
  							</p>

  						</div>
  					</div>
  					<span class="bl-icon bl-icon-close"></span>
  				</section>




  				<!--=========== PROJECTS DESCRIPTIONS SECTION ===========-->
  				<div class="bl-panel-items" id="bl-panel-work-items">
  					<div data-panel="panel-1">
  						<div class="row-fluid">
  							<div class="span8 offset2 tweak">
  								<img src="${pageContext.request.contextPath}/resources/startAssets/images/project1.jpg" />
  								<br>
  								<br>
  								<h4>Project Overview</h4>
  								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>
  								<h4>Project Details</h4>
  								<p>When an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
  							</div><!-- /span8 -->
  						</div><!-- /row-fluid -->	
  					</div><!-- /panel1 -->

  					<div data-panel="panel-2">
  						<div class="row-fluid">
  							<div class="span8 offset2 tweak">
  								<img src="${pageContext.request.contextPath}/resources/startAssets/images/project2.jpg" />
  								<br>
  								<br>
  								<h4>Project Overview</h4>
  								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>
  								<h4>Project Details</h4>
  								<p>When an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
  							</div><!-- /span8 -->
  						</div><!-- /row-fluid -->	
  					</div><!-- /panel2 -->

  					<div data-panel="panel-3">
  						<div class="row-fluid">
  							<div class="span8 offset2 tweak">
  								<img src="${pageContext.request.contextPath}/resources/startAssets/images/project3.jpg" />
  								<br>
  								<br>
  								<h4>Project Overview</h4>
  								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>
  								<h4>Project Details</h4>
  								<p>When an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
  							</div><!-- /span8 -->
  						</div><!-- /row-fluid -->	
  					</div><!-- /panel3 -->

  					<nav>
  						<span class="bl-next-work">&gt; Next Project</span>
  						<span class="bl-icon bl-icon-close"></span>
  					</nav>
  				</div><!-- /panel-items -->


  			</div><!-- /bl-main -->

  		</div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/startAssets/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/startAssets/js/bootstrap-modal.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/startAssets/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/startAssets/js/boxlayout.js"></script>
    <script>
    	$(function() {
    		Boxlayout.init();
    	});
    </script>
</body>
</html>