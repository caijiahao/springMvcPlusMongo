/* ==========================================================
 * QuickAdmin v1.3.1
 * ui.js
 * 
 * http://www.mosaicpro.biz
 * Copyright MosaicPro
 *
 * Built exclusively for sale @Envato Marketplaces
 * ========================================================== */ 

$(function()
{
	/* 
	 * JQuery Pagination Examples 
	 */
	
	$('.jquery-bootpag-pagination').bootpag({
	   total: 23,
	   page: 1,
	   maxVisible: 10 
	}).on('page', function(event, num){
	    $(".jquery-bootpag-content").html("Page " + num); // or some ajax content loading ...
	});
	
});