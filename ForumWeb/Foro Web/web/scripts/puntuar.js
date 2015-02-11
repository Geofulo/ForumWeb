
var imgs=document.getElementById('setrating').getElementsByTagName('input');
 for (i = 0; i < imgs.length; i++) {
      imgs[i].addEventListener("mouseover", function(e) {
         setRating(parseInt(e.target.alt) + 1)
      }, false);
 }

 function setRating(point) {
	 stars = new Array("R1","R2","R3","R4","R5");
	 start = parseInt(point);
	 for(i=0;i<5;i++)
	 {
	 if(i >= start)
	   document.getElementById(stars[i]).src="images/rate0.png";
	 if(i < parseInt(point))
	   document.getElementById(stars[i]).src="images/rate1.png";
	 }
}

