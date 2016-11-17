/* ==========================================================
 * QuickAdmin v1.3.1
 * choose.js
 * 
 * http://www.mosaicpro.biz
 * Copyright MosaicPro
 *
 * Built exclusively for sale @Envato Marketplaces
 * ========================================================== */ 

$(function()
{
	var url_default = [];
		url_default['admin'] = $('#choose-preview .options[data-for="admin"] .actions a').attr('href');
		url_default['front'] = $('#choose-preview .options[data-for="front"] .actions a').attr('href');
	
	$('#choose-preview')
		.find('.options select')
		.on('change', function()
		{
			var box = $(this).parents('.box:first');
			var $for = $(this).attr('data-for');
			var select_layout = box.find('select[data-type="layout"]');
			var select_sidebar = box.find('select[data-type="sidebar"]');
			var select_sidebar_position = box.find('select[data-type="menu"]');
			var select_sidebar_sticky = box.find('select[data-type="sidebar-sticky"]');
			var select_top_sticky = box.find('select[data-type="top-sticky"]');
			var select_rtl = box.find('select[data-type="rtl"]');
			var select_style = box.find('select[data-type="style"]');
			
			var url = url_default[$for];
			
			if (select_layout.length)
				url += '&layout_type=' + select_layout.val();
			if (select_sidebar.length)
				url += '&sidebar=' + select_sidebar.val();
			if (select_sidebar_position.length)
				url += '&menu_position=' + select_sidebar_position.val();
			if (select_sidebar_sticky.length) 
				url += '&sidebar-sticky=' + select_sidebar_sticky.val();
			if (select_top_sticky.length) 
				url += '&top-sticky=' + select_top_sticky.val();
			if (select_rtl.length) 
				url += '&rtl=' + select_rtl.val();
			if (select_style.length) 
				url += '&style=' + select_style.val();
			
			$('#choose-preview .options[data-for="'+$for+'"] .actions a').attr('href', url);
			
			/*
			var selector = '#' + $for + '-' + select_style.val();
			if (select_layout.length) 
				selector += '-' + select_layout.val();
			if (select_menu.length) 
				selector += '-' + select_menu.val();
			if (select_rtl.length) 
				selector += '-rtl-' + select_rtl.val();
			
			box.find('.actions a').removeClass('btn-active');
			$(selector).addClass('btn-active');
			
			console.log(selector);
			*/
		});
});