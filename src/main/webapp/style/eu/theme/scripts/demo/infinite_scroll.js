/* ==========================================================
 * QuickAdmin v1.3.1
 * infinite_scroll.js
 * 
 * http://www.mosaicpro.biz
 * Copyright MosaicPro
 *
 * Built exclusively for sale @Envato Marketplaces
 * ========================================================== */ 

$(function()
{
	var isCustomScroll = $('body').css('overflow') === 'hidden';

	$('.jscroll').jscroll({
	    loadingHtml: '<div class="alert alert-primary center">Loading ...</div>',
	    debug: false,
	    nextSelector: '.jscroll-next:last',
	    isCustomScroll: isCustomScroll,
	    isWindow: !isCustomScroll,
	    customScroll: isCustomScroll ? '#wrapper' : 'window'
	});
});