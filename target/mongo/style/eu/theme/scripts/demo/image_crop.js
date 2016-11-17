/* ==========================================================
 * QuickAdmin v1.3.1
 * image_crop.js
 * 
 * http://www.mosaicpro.biz
 * Copyright MosaicPro
 *
 * Built exclusively for sale @Envato Marketplaces
 * ========================================================== */ 

$(function()
{
	$('#jcrop-target-1').Jcrop({},function(){
		api = this;
		api.setSelect([130,65,130+350,65+285]);
		api.setOptions({ bgFade: true });
		api.ui.selection.addClass('jcrop-selection');
	});
	
	/*
	 * JCrop with preview Example
	 */
	// Create variables (in this scope) to hold the API and image size
    var jcrop_api,
        boundx,
        boundy,

        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),

        xsize = $pcnt.width(),
        ysize = $pcnt.height();
	
	function handleTarget2()
	{   
	    $('#jcrop-target-2').Jcrop({
	    	onChange: updatePreview,
	    	onSelect: updatePreview,
	    	aspectRatio: xsize / ysize
	    },function(){
	    	// Use the API to get the real image size
	    	var bounds = this.getBounds();
	    	boundx = bounds[0];
	    	boundy = bounds[1];
	    	// Store the API in the jcrop_api variable
	    	jcrop_api = this;
	    	
	    	jcrop_api.setSelect([130,65,130+350,65+285]);
	    	jcrop_api.setOptions({ bgFade: true });
	    	jcrop_api.ui.selection.addClass('jcrop-selection');

	    	// Move the preview into the jcrop container for css positioning
	    	$preview.appendTo(jcrop_api.ui.holder);
	    });
	}
	
	function updatePreview(c)
	{
		if (parseInt(c.w) > 0)
		{
			var rx = xsize / c.w;
			var ry = ysize / c.h;

			$pimg.css({
				width: Math.round(rx * boundx) + 'px',
				height: Math.round(ry * boundy) + 'px',
				marginLeft: '-' + Math.round(rx * c.x) + 'px',
				marginTop: '-' + Math.round(ry * c.y) + 'px'
			});
		}
	};
	
	handleTarget2();
});