/* ==========================================================
 * QuickAdmin v1.3.1
 * tour_demo.js
 * 
 * http://www.mosaicpro.biz
 * Copyright MosaicPro
 *
 * Built exclusively for sale @Envato Marketplaces
 * ========================================================== */ 

$(function()
{
	if (!$('#tlyPageGuide').length)
		return false;
	
	tl.pg.init({
		custom_open_button: '#tour-demo-start'
	});
	
	setTimeout(function(){
		$('#tour-demo-start').click();
	}, 1000);

});