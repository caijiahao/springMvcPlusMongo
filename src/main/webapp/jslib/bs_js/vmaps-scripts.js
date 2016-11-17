var Script = function () {

    //    world vmap
    jQuery(document).ready(function() {
        jQuery('#vmap_world').vectorMap({
            map: 'world_en',
            backgroundColor: '#333333',
            color: '#ffffff',
            hoverOpacity: 0.7,
            selectedColor: '#666666',
            enableZoom: true,
            showTooltip: true,
            values: sample_data,
            scaleColors: ['#C8EEFF', '#006491'],
            normalizeFunction: 'polynomial'
        });
    });

    //    russia vmap
    jQuery(document).ready(function() {
        jQuery('#vmap_russia').vectorMap({
            map: 'russia_en',
            backgroundColor: '#333333',
            color: '#ffffff',
            hoverOpacity: 0.7,
            selectedColor: '#999999',
            enableZoom: true,
            showTooltip: true,
            values: sample_data,
            scaleColors: ['#C8EEFF', '#006491'],
            normalizeFunction: 'polynomial'
        });
    });

    //    germany vmap
    jQuery(document).ready(function() {
        jQuery('#vmap_germany').vectorMap({
            map: 'germany_en',
            onRegionClick: function(element, code, region)
            {
                var message = 'You clicked "'
                    + region
                    + '" which has the code: '
                    + code.toUpperCase();

                alert(message);
            }
        });
    });

    //    usa vmap
    jQuery(document).ready(function() {
        jQuery('#vmap_usa').vectorMap({
            map: 'usa_en',
            enableZoom: true,
            showTooltip: true,
            selectedRegion: 'MO'
        });
    });

    //    europe vmap
    jQuery(document).ready(function() {
        jQuery('#vmap_europe').vectorMap({
            map: 'europe_en',
            enableZoom: false,
            showTooltip: false
        });
    });


}();