<%@ page contentType = "text/html; charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
<head>

	<!-- Meta Tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<title>Chakra | Responsive One Page Template</title>   

	<meta name="description" content="" /> 

	<!-- Mobile Specifics -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="HandheldFriendly" content="true"/>
	<meta name="MobileOptimized" content="320"/>   

	<!-- Mobile Internet Explorer ClearType Technology -->
	<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->


	<!-- Bootstrap -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/bootstrap.min.css" rel="stylesheet">

	<!-- Main Style -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/main.css" rel="stylesheet">

	<!-- Supersized -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/supersized.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/_include/css/supersized.shutter.css" rel="stylesheet">

	<!-- FancyBox -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">

	<!-- Font Icons -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/fonts.css" rel="stylesheet">

	<!-- Shortcodes -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/shortcodes.css" rel="stylesheet">

	<!-- Responsive -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/_include/css/responsive.css" rel="stylesheet">

	<!-- Custom CSS -->
	<link href="${pageContext.request.contextPath}/resources/_include/css/custom.css" rel="stylesheet">

	<!-- Google Font -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>

	<!-- Fav Icon -->
	<link rel="shortcut icon" href="#">

	<link rel="apple-touch-icon" href="#">
	<link rel="apple-touch-icon" sizes="114x114" href="#">
	<link rel="apple-touch-icon" sizes="72x72" href="#">
	<link rel="apple-touch-icon" sizes="144x144" href="#">

	<!-- Modernizr -->
	<script src="${pageContext.request.contextPath}/resources/_include/js/modernizr.js"></script>

	<!-- Analytics -->
	<script type="text/javascript">

		var _gaq = _gaq || [];
		_gaq.push(['_setAccount', 'YOUR ID']);
		_gaq.push(['_trackPageview']);

		(function() {
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		})();

	</script>
	<!-- End Analytics -->

</head>


<body>

	<!-- This section is for Splash Screen -->
	<div class="ole">
		<section id="jSplash">
			<div id="circle"></div>
		</section>
	</div>
	<!-- End of Splash Screen -->

	<!-- Homepage Slider -->
	<div id="home-slider">	
		<div class="overlay">
		</div>

		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="slider-text">
						<div id="slidecaption"></div>
					</div>
				</div>
			</div>
		</div>   

		<div class="control-nav">
			<a id="prevslide" class="load-item"><i class="font-icon-arrow-simple-left"></i></a>
			<a id="nextslide" class="load-item"><i class="font-icon-arrow-simple-right"></i></a>
			<ul id="slide-list"></ul>

			<a id="nextsection" href="#work"><i class="font-icon-arrow-simple-down"></i></a>
		</div>
	</div>
	<!-- End Homepage Slider -->

	<!-- Header -->
	<header>
		<div class="sticky-nav">
			<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>

			<div id="logo">
				<a id="goUp" href="#home-slider" title="Chakra | Responsive One Page Template">Chakra Template</a>
			</div>

			<nav id="menu">
				<ul id="menu-nav">
					<li class="current"><a href="#home-slider">Treezing</a></li>
					<li><a href="#course">My course</a></li>
					<li><a href="#profile">My Profile</a></li>
					<li><a href="#services">Ranking</a></li>
					<li><a href="#services">Tutorial</a></li>
					<li><a href="blog.html" class="external">Feedback</a></li>


				</ul>
			</nav>

		</div>
	</header>
	<!-- End Header -->

	<!-- Our Work Section -->
	<div id="course" class="page">
		<div class="container">


			<!-- Portfolio Projects -->
			<div class="row">
				<div class="span12">
					<!-- Filter -->
					<nav id="options" class="work-nav">
						<ul id="filters" class="option-set" data-option-key="filter">
							<li class="type-work">Course Management</li>
							<li><a href="#filter" data-option-value="*" class="selected">All Lecture</a></li>
							<li><a href="#filter" data-option-value=".design">My Course</a></li>
							<li><a href="#filter" data-option-value=".illustration">Category Of Lecture</a></li>
							<li><a href="#filter" data-option-value=".photography">Photography</a></li>
							<li><a href="#filter" data-option-value=".video">Video</a></li>
						</ul>
					</nav>
					<!-- End Filter -->
				</div>

				<div class="span12">
					<div class="row">

						<!-- Course Management List -->

		

		
						<a class="btn btn-info pull-right" style="margin-left:10px" href="addMypage.html">수강 신청 
						</a>

						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Lecture Name</th>
									<th>Create Date</th>
									<th>Personnel</th>

								</tr>
							</thead>
							<tbody>
								<tr >
									<td>1</td>
									<td> <a href="#">미분적분학 </a></td>
									<td>2013.8.29</td>
									<td>23</td>
									<td>  
										<div class="checkbox">
											<label>
											<input type="checkbox">
											</label>
										</div>
									</td>

								</tr>
								<tr >
									<td>2</td>
									<td><a href="#">컴퓨터구조</a></td>
									<td>2013.9.3</td>
									<td>52</td>
		<td>  
										<div class="checkbox">
											<label>
											<input type="checkbox">
											</label>
										</div>
									</td>

								</tr>
								<tr >
									<td>3</td>
									<td>알고리즘</td>
									<td>2012.2.4</td>
									<td>43</td>
		<td>  
										<div class="checkbox">
											<label>
											<input type="checkbox">
											</label>
										</div>
									</td>

								</tr>
							</tbody>
						</table>
						<div class="pagination pagination-centered">
							<ul>
								<li class="disabled"><a href="#">«</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">»</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- End Portfolio Projects -->
		</div>
	</div>
	<!-- End Our Work Section -->

	<!-- About Section -->
	<div id="profile" class="page-alternate">
		<div class="container">
			<!-- Title Page -->
			<div class="row">
				<div class="span12">
					<div class="title-page">
						<h2 class="title">My Profile</h2>


						<div class="page-description">

							<!-- Edit profile -->

						</div>

					</div>
				</div>
			</div>
			<!-- End Title Page -->

			<div class="row margin-40">
				<div class="span4">
					<h3>Our Culture</h3>
					<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed ligula odio. Sed id metus felis. Ut pretium nisl non justo condimentum id tincidunt nunc faucibus. 
						Ut neque eros, pulvinar eu blandit quis, lacinia nec ipsum. Etiam vel orci ipsum. Sed eget velit ipsum. </p>

						<p>Duis in tortor scelerisque felis mattis imperdiet. Donec at <a href="#">libero tellus</a>. Suspendisse consectetur consectetur bibendum.</p>
					</div>

					<div class="span4">
						<h3>Our Method</h3>
						<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed ligula odio. Sed id metus felis. Ut <strong>pretium nisl</strong> non justo condimentum id tincidunt nunc faucibus. 
							Ut neque eros, pulvinar eu blandit quis, lacinia nec ipsum. Etiam vel orci ipsum. Sed eget velit ipsum. </p>

							<p>Duis in tortor scelerisque felis mattis imperdiet. Donec at libero tellus. Suspendisse consectetur consectetur bibendum.</p>
						</div>

						<div class="span4">
							<h3>Our Skills</h3>

							<div class="progress-bar">
								<div class="progress">
									<span class="field">Design</span>
									<span class="field-value">60%</span>
									<div class="bar" style="width: 60%;"></div>
								</div>
							</div>

							<div class="progress-bar">
								<div class="progress">
									<span class="field">Development</span>
									<span class="field-value">75%</span>
									<div class="bar" style="width: 75%;"></div>
								</div>
							</div>

							<div class="progress-bar">
								<div class="progress">
									<span class="field">UX/UI</span>
									<span class="field-value">33%</span>
									<div class="bar" style="width: 33%;"></div>
								</div>
							</div>

							<div class="progress-bar">
								<div class="progress">
									<span class="field">Branding</span>
									<span class="field-value">100%</span>
									<div class="bar" style="width: 100%;"></div>
								</div>
							</div>    
						</div>

					</div>

					<div class="sep">
						<span class="separator"></span>
					</div>

					<!-- People -->
					<div class="row">

						<!-- Start Profile -->
						<div class="span4 profile">
							<div class="image-wrap">
								<div class="hover-wrap">
									<span class="overlay-img"></span>
									<span class="overlay-text-thumb">CTO/Founder</span>
								</div>
								<img src="${pageContext.request.contextPath}/resources/_include/img/profile/profile-01.jpg" alt="John Doe">
							</div>
							<h3 class="profile-name">John Doe</h3>
							<p class="profile-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac augue at erat <a href="#">hendrerit dictum</a>. 
								Praesent porta, purus eget sagittis imperdiet, nulla mi ullamcorper metus, id hendrerit metus diam vitae est. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>

								<div class="social">
									<ul class="social-icons">
										<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
										<li><a href="#"><i class="font-icon-social-dribbble"></i></a></li>
										<li><a href="#"><i class="font-icon-social-facebook"></i></a></li>
									</ul>
								</div>
							</div>
							<!-- End Profile -->

							<!-- Start Profile -->
							<div class="span4 profile">
								<div class="image-wrap">
									<div class="hover-wrap">
										<span class="overlay-img"></span>
										<span class="overlay-text-thumb">Creative Director</span>
									</div>
									<img src="${pageContext.request.contextPath}/resources/_include/img/profile/profile-02.jpg" alt="Jane Helf">
								</div>
								<h3 class="profile-name">Jane Helf</h3>
								<p class="profile-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac augue at erat <a href="#">hendrerit dictum</a>. 
									Praesent porta, purus eget sagittis imperdiet, nulla mi ullamcorper metus, id hendrerit metus diam vitae est. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>

									<div class="social">
										<ul class="social-icons">
											<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
											<li><a href="#"><i class="font-icon-social-email"></i></a></li>
										</ul>
									</div>
								</div>
								<!-- End Profile -->

								<!-- Start Profile -->
								<div class="span4 profile">
									<div class="image-wrap">
										<div class="hover-wrap">
											<span class="overlay-img"></span>
											<span class="overlay-text-thumb">Lead Designer</span>
										</div>
										<img src="${pageContext.request.contextPath}/resources/_include/img/profile/profile-03.jpg" alt="Joshua Insanus">
									</div>
									<h3 class="profile-name">Joshua Insanus</h3>
									<p class="profile-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac augue at erat <a href="#">hendrerit dictum</a>. 
										Praesent porta, purus eget sagittis imperdiet, nulla mi ullamcorper metus, id hendrerit metus diam vitae est. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>

										<div class="social">
											<ul class="social-icons">
												<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
												<li><a href="#"><i class="font-icon-social-linkedin"></i></a></li>
												<li><a href="#"><i class="font-icon-social-google-plus"></i></a></li>
												<li><a href="#"><i class="font-icon-social-vimeo"></i></a></li>
											</ul>
										</div>
									</div>
									<!-- End Profile -->

								</div>
								<!-- End People -->
							</div>
						</div>
						<!-- End About Section -->

						<!-- Big Blockquote -->
						<div class="big-quote page">
							<div class="container">
								<div class="row">
									<div class="span12">
										<p>We bring a personal and effective approach to every project we work on, which is why our clients love us and why they keep coming back.</p>
										<span class="font-icon-blockquote"></span>
									</div>
								</div>
							</div>
						</div>
						<!-- End Big Blockquote -->

						<!-- Services Section -->
						<div id="services" class="page-alternate">
							<div class="container">
								<!-- Title Page -->
								<div class="row">
									<div class="span12">
										<div class="title-page">
											<h2 class="title">Services</h2>
											<h3 class="title-description">The key to a successful network operation is to provide high quality services.</h3>

											<div class="page-description">
												<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed ligula odio. Sed id metus felis. Ut pretium nisl non justo condimentum id tincidunt nunc faucibus. 
													Ut neque eros, pulvinar eu blandit quis, lacinia nec ipsum. Etiam vel orci ipsum. Sed eget velit ipsum. Duis in tortor scelerisque felis mattis imperdiet. Donec at libero tellus. 
													<a href="#">Suspendisse consectetur</a> consectetur bibendum. Pellentesque posuere, ligula volutpat elementum interdum, diam arcu elementum ipsum, vel ultricies est mauris ut nisi.</p>
												</div>

											</div>
										</div>
									</div>
									<!-- End Title Page -->

									<div class="row margin-40">
										<div class="span9">
											<h3>Our Technology</h3>
											<p>Sed at odio ut arcu fringilla dictum eu eu nisl. Donec rutrum erat non arcu gravida porttitor. Nunc et magna nisi.Aliquam at erat in purus aliquet mollis. Fusce elementum velit vel dolor iaculis egestas. 
												Maecenas ut nulla quis eros scelerisque posuere vel vitae nibh. Proin id condimentum sem. Morbi vitae dui in magna <a href="#">vestibulum suscipit</a> vitae vel nunc. Integer ut risus nulla.</p>

												<p>Malesuada tortor, nec scelerisque lorem mattis. Nunc et rutrum consetetur sadipscing elitr, sed diam nonumy at volutpat. Sed consectetur <em>suscipit lorem</em> nunc adipiscing elit. Integer commodo tristique odio, quis fringilla ligula aliquet ut. 
													Maecenas sed justo varius velit imperdiet bibendum et rutrum.</p>

													<p>Malesuada tortor, nec scelerisque lorem mattis. Nunc et rutrum consetetur sadipscing elitr, sed diam nonumy at volutpat. <strong>Sed consectetur</strong> suscipit lorem nunc adipiscing elit. 
														Integer commodo tristique odio, quis fringilla ligula aliquet ut. Maecenas sed justo varius velit imperdiet bibendum et rutrum.</p> 
													</div>

													<div class="span3">
														<h3>Why Choose Us <span class="color-text">?</span></h3>
														<ul>
															<li>Lorem ipsum dolor</li>
															<li>Lorem ipsum dolor</li>
															<li>Lorem ipsum dolor</li>
															<li>Lorem ipsum dolor</li>
														</ul>
													</div>
												</div>

												<div class="sep">
													<span class="separator"></span>
												</div>

												<!-- Services Boxes -->
												<div class="row">
													<div class="span4">
														<div class="services-box">
															<div class="icon">
																<span class="font-icon-cog"></span>
															</div>
															<h3>Deployment</h3>
															<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed ligula odio. Sed id metus felis. Ut pretium nisl non justo condimentum id tincidunt nunc faucibus. Ut neque eros, pulvinar eu blandit quis, lacinia nec.</p>
														</div>
													</div>

													<div class="span4">
														<div class="services-box">
															<div class="icon">
																<span class="font-icon-layer"></span>
															</div>
															<h3>Optimization</h3>
															<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed ligula odio. Sed id metus felis. Ut pretium nisl non justo condimentum id tincidunt nunc faucibus. Ut neque eros, pulvinar eu blandit quis, lacinia nec.</p>
														</div>
													</div>

													<div class="span4">
														<div class="services-box">
															<div class="icon">
																<span class="font-icon-map"></span>
															</div>
															<h3>Manages Services</h3>
															<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed ligula odio. Sed id metus felis. Ut pretium nisl non justo condimentum id tincidunt nunc faucibus. Ut neque eros, pulvinar eu blandit quis, lacinia nec.</p>
														</div>
													</div>
												</div>
												<!-- End Services Boxes -->

											</div>
										</div>
										<!-- End Services Section -->

										<!-- Google Map -->
										<div id="map-area">
											<div class="map" id="map_1" data-mapLat="51.911475" data-mapLon="-1.461815" data-mapZoom="11" data-mapTitle="Your Title"></div>
										</div>
										<!-- End Google Map -->

										<!-- Contact Section -->
										<div id="contact" class="page">
											<div class="container">
												<!-- Title Page -->
												<div class="row">
													<div class="span12">
														<div class="title-page">
															<h2 class="title">Get in Touch</h2>
															<h3 class="title-description">Weâre currently accepting new client projects. We look forward to serving you.</h3>

															<div class="page-description">
																<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed ligula odio. Sed id metus felis. Ut pretium nisl non justo condimentum id tincidunt nunc faucibus. 
																	Ut neque eros, pulvinar eu blandit quis, lacinia nec ipsum. Etiam vel orci ipsum. Sed eget velit ipsum. Duis in tortor scelerisque felis mattis imperdiet. Donec at libero tellus. 
																	<a href="#">Suspendisse consectetur</a> consectetur bibendum. Pellentesque posuere, ligula volutpat elementum interdum, diam arcu elementum ipsum, vel ultricies est mauris ut nisi.</p>
																</div>
															</div>
														</div>
													</div>
													<!-- End Title Page -->

													<!-- Contact Form -->
													<div class="row">
														<div class="span9">

															<form id="contact-form" class="contact-form" action="#">
																<p class="contact-name">
																	<input id="contact_name" type="text" placeholder="Full Name" value="" name="name" />
																</p>
																<p class="contact-email">
																	<input id="contact_email" type="text" placeholder="Email Address" value="" name="email" />
																</p>
																<p class="contact-message">
																	<textarea id="contact_message" placeholder="Your Message" name="message" rows="15" cols="40"></textarea>
																</p>
																<p class="contact-submit">
																	<a id="contact-submit" class="submit" href="#">Send Your Email</a>
																</p>

																<div id="response">

																</div>
															</form>

														</div>

														<div class="span3">
															<div class="contact-details">
																<h3>Contact Details</h3>
																<ul>
																	<li><a href="#">hello@chakra.com</a></li>
																	<li>(916) 375-2525</li>
																	<li>
																		Chakra Studio
																		<br>
																		5240 Vanish Island. 105
																		<br>
																		Unknow
																	</li>
																</ul>
															</div>
														</div>
													</div>
													<!-- End Services Form -->
												</div>
											</div>
											<!-- End Contact Section -->

											<!-- Twitter Feed -->
											<div id="twitter-feed" class="page-alternate">
												<div class="container">
													<div class="row">
														<div class="span12">
															<div class="follow">
																<a href="https://twitter.com/Bluxart" title="Follow Me on Twitter" target="_blank"><i class="font-icon-social-twitter"></i></a>
															</div>

															<div id="ticker" class="query"> 
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- End Twitter Feed -->

											<!-- Socialize -->
											<div id="social-area" class="page">
												<div class="container">
													<div class="row">
														<div class="span12">
															<nav id="social">
																<ul>
																	<li><a href="https://twitter.com/Bluxart" title="Follow Me on Twitter" target="_blank"><span class="font-icon-social-twitter"></span></a></li>
																	<li><a href="http://dribbble.com/Bluxart" title="Follow Me on Dribbble" target="_blank"><span class="font-icon-social-dribbble"></span></a></li>
																	<li><a href="http://forrst.com/people/Bluxart" title="Follow Me on Forrst" target="_blank"><span class="font-icon-social-forrst"></span></a></li>
																	<li><a href="http://www.behance.net/alessioatzeni" title="Follow Me on Behance" target="_blank"><span class="font-icon-social-behance"></span></a></li>
																	<li><a href="https://www.facebook.com/Bluxart" title="Follow Me on Facebook" target="_blank"><span class="font-icon-social-facebook"></span></a></li>
																	<li><a href="https://plus.google.com/105500420878314068694" title="Follow Me on Google Plus" target="_blank"><span class="font-icon-social-google-plus"></span></a></li>
																	<li><a href="http://www.linkedin.com/in/alessioatzeni" title="Follow Me on LinkedIn" target="_blank"><span class="font-icon-social-linkedin"></span></a></li>
																	<li><a href="http://themeforest.net/user/Bluxart" title="Follow Me on ThemeForest" target="_blank"><span class="font-icon-social-envato"></span></a></li>
																	<li><a href="http://zerply.com/Bluxart/public" title="Follow Me on Zerply" target="_blank"><span class="font-icon-social-zerply"></span></a></li>
																</ul>
															</nav>
														</div>
													</div>
												</div>
											</div>
											<!-- End Socialize -->

											<!-- Footer -->
											<footer>
												<p class="credits">&copy;2013 Chakra. <a href="http://themes.alessioatzeni.com/html/chakra/" title="Chakra | Responsive One Page Template">Chakra Template</a> by <a href="http://www.alessioatzeni.com/" title="Alessio Atzeni | Web Designer &amp; Front-end Developer">Alessio Atzeni</a></p>
											</footer>
											<!-- End Footer -->

											<!-- Back To Top -->
											<a id="back-to-top" href="#">
												<i class="font-icon-arrow-simple-up"></i>
											</a>
											<!-- End Back to Top -->


											<!-- Js -->
											<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
											<script src="https://maps.googleapis.com/maps/api/js?sensor=true"></script> <!-- Google Map API -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/supersized.3.2.7.min.js"></script> <!-- Slider -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/waypoints.js"></script> <!-- WayPoints -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/jquery.tweet.js"></script> <!-- Tweet -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
											<script src="${pageContext.request.contextPath}/resources/_include/js/main.js"></script> <!-- Default JS -->
											<!-- End Js -->

										</body>
										</html>