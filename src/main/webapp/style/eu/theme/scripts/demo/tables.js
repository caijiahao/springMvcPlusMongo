/* ==========================================================
 * QuickAdmin v1.3.1
 * tables.js
 * 
 * http://www.mosaicpro.biz
 * Copyright MosaicPro
 *
 * Built exclusively for sale @Envato Marketplaces
 * ========================================================== */ 

$(function()
{
	/* DataTables */
	if ($('.dynamicTable').size() > 0)
	{
		$('.dynamicTable').each(function()
		{
			// DataTables with TableTools
			if ($(this).is('.tableTools'))
			{
				$(this).dataTable({
					"sPaginationType": "bootstrap",
					"sDom": "<'row-fluid'<'span5'T><'span3'l><'span4'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"oLanguage": {
						"sLengthMenu": "_MENU_ per page"
					},
					"oTableTools": {
				        "sSwfPath": commonPath + "theme/scripts/plugins/tables/DataTables/extras/TableTools/media/swf/copy_csv_xls_pdf.swf"
				    }
				});
			}
			// colVis extras initialization
			else if ($(this).is('.colVis'))
			{
				$(this).dataTable({
					"sPaginationType": "bootstrap",
					"sDom": "<'row-fluid'<'span3'f><'span3'l><'span6'C>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"oLanguage": {
						"sLengthMenu": "_MENU_ per page"
					}
				});
			}
			// default initialization
			else
			{
				$(this).dataTable({
					"sPaginationType": "bootstrap",
					"sDom": "<'row-fluid'<'span5'T><'span3'l><'span4'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"oLanguage": {
						"sLengthMenu": "_MENU_ per page"
					}
				});
			}
		});
	}
});