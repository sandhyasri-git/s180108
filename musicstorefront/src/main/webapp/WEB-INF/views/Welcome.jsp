<%@include file="Header.jsp" %>
  <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 70%;
      margin: auto;
      height:50%;
  }
/*  .container
 {
  background-image:url('images/pic1.jpg');
 } */
  </style>
  
<body>
<div class="container">
		<br>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
				<li data-target="#myCarousel" data-slide-to="4"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox" style="margin:auto;height:100%;width:500px !important;">
				<div class="item active">
					<img src="<c:url value="images/music6.jpg"/>"
						alt="Music" width="460" height="300">
						
				</div>

				<div class="item">
					<img src="<c:url value="images/music8.jpg"/>"
						alt="Music" width="0" height="0">
				</div>

				<div class="item">
					<img src="<c:url value="images/music9.jpg"/>"
						alt="Music" width="0" height="0">
				</div>

				<div class="item">
					<img src="<c:url value="images/music11.jpg"/>">
						<!-- alt="Music" width="0" height="0"> -->
				</div>
				<div class="item">
					<img src="<c:url value="images/music12.jpg"/>"
						alt="Music" width="0" height="0">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>



<%@include file="Footer.jsp"%>