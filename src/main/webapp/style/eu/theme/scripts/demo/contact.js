/* ==========================================================
 * QuickAdmin v1.3.1
 * contact.js
 * 
 * http://www.mosaicpro.biz
 * Copyright MosaicPro
 *
 * Built exclusively for sale @Envato Marketplaces
 * ========================================================== */ 

/* Google Maps API */
if (typeof google != 'undefined')
{
	var map_latlng = new google.maps.LatLng(47.06285,21.943721);
	var map_options = {
			zoom: 1,
			center: map_latlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP,
			panControl: false,
			zoomControl: false,
			scaleControl: false,
			mapTypeControl: false,
			disableDefaultUI: true,
			scrollwheel: false,
			styles: [{
				stylers: [{ 
					saturation: -20
				}, { 
					hue: themerPrimaryColor 
				}]
			}, {
				elementType: "labels.text.fill",
				stylers: [{ color: "#444444" }]
			    //stylers: [{ color: primaryColor }]
			}]
	};

	var markerIconDefault_image = new google.maps.MarkerImage(commonPath + "theme/images/marker.png",
			// This marker is 44 pixels wide by 56 pixels tall.
			new google.maps.Size(44, 56),
			// The origin for this image is 0,0.
			new google.maps.Point(0, 0),
			// The anchor for this image is the base of the flagpole at 0,32.
			new google.maps.Point(22, 56));

	var markerIconDefault_shadow = new google.maps.MarkerImage(commonPath + "theme/images/marker_shadow.png",
			// This marker is 44 pixels wide by 56 pixels tall.
			new google.maps.Size(37, 21),
			// The origin for this image is 0,0.
			new google.maps.Point(0,0),
			// The anchor for this image is the base of the flagpole at 0,32.
			new google.maps.Point(20, 10));

	var markerIconDefault_shape = {
			coord: [1, 1, 1, 52, 42, 52, 42 , 1],
			type: 'poly'
	};

}

function initializeMap(el, options)
{
	if (typeof google == 'undefined') 
		return false;

	var map = new google.maps.Map(document.getElementById(el), options);
	var marker = new google.maps.Marker({
		position: map_latlng,
		title: 'My Location',
		shadow: markerIconDefault_shadow,
		icon: markerIconDefault_image,
		map: map
	});
}

$(function()
{
	// Contact Page Google Maps
    if ($('#contact_gmap').size() > 0 && typeof google != 'undefined')
    {
    	map_options.zoom = 13;
    	initializeMap('contact_gmap', map_options);
    }
});