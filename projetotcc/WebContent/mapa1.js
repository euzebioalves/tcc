 var map;

    function init() {
    	
    	vlayer = new OpenLayers.Layer.Vector( "Editable" );
        map = new OpenLayers.Map('map', {
            controls: [
                       new OpenLayers.Control.PanZoom(),
                       new OpenLayers.Control.EditingToolbar(vlayer)
                   ]
               ,maxResolution: 0.12563114453125, projection: new OpenLayers.Projection("EPSG:900913"), displayProjection: new OpenLayers.Projection("EPSG:4326")});
        map.addControl(new OpenLayers.Control.LayerSwitcher());
        
        // the SATELLITE layer has all 22 zoom level, so we add it first to
        // become the internal base layer that determines the zoom levels of the
        // map.
        var gsat = new OpenLayers.Layer.Google(
            "Google Satellite",
            {type: google.maps.MapTypeId.SATELLITE, numZoomLevels: 22, visibility: false}
        );
        var gphy = new OpenLayers.Layer.Google(
            "Google Physical",
            {type: google.maps.MapTypeId.TERRAIN, visibility: false}
        );
        var gmap = new OpenLayers.Layer.Google(
            "Google Streets", // the default
            {numZoomLevels: 20, visibility: false}
        );
        var ghyb = new OpenLayers.Layer.Google(
            "Google Hybrid",
            {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 22}
        );
        var wktLayer = new OpenLayers.Layer.Vector("wktLayer");
       // map.addLayer(wktLayer);
        var cities = new OpenLayers.Layer.WMS("Cities",
                "http://demo.opengeo.org/geoserver/wms", 
                {'layers': 'topp:tasmania_cities', transparent: true, format: 'image/gif'},
                {isBaseLayer: false}
            );
        
        
        
        map.addLayers([ghyb, gphy, gsat, gmap, wktLayer, vlayer, cities]);
        var wkt = new OpenLayers.Format.WKT();
        var features = wkt.read("POLYGON((-48.990354537964 -16.940640217331,-48.991041183472 -16.940065472703,-48.989839553833 -16.938587549881,-48.977308273315 -16.948358046473,-48.975677490234 -16.948029634664,-48.973875045776 -16.94819384064,-48.97198677063 -16.948604354953,-48.97087097168 -16.948604354953,-48.969841003418 -16.949589585646,-48.970355987549 -16.950246403239,-48.970699310303 -16.951395828506,-48.971643447876 -16.95238104457,-48.972845077515 -16.952627347778,-48.973531723022 -16.953202054011,-48.976707458496 -16.953284154758,-48.977479934692 -16.953940959442,-48.978252410889 -16.953202054011,-48.981857299805 -16.95238104457,-48.982715606689 -16.952134741038,-48.983488082886 -16.952298943428,-48.986749649048 -16.954105160254,-48.987865447998 -16.95558296111,-48.988466262817 -16.957307047421,-48.988380432129 -16.958620626374,-48.991556167603 -16.958045936712,-49.002199172974 -16.952298943428,-49.000825881958 -16.949014868369,-48.996105194092 -16.945648631896,-48.994302749634 -16.943513914128,-48.991212844849 -16.939983366184,-48.990354537964 -16.940640217331))");
        wktLayer.addFeatures(features);
        alert('');
        
       // map.setCenter(new OpenLayers.LonLat(-99.260743, -16.676978),6); 
        //alert('');
        var geom = OpenLayers.Geometry.fromWKT("POLYGON((-48.990354537964 -16.940640217331,-48.991041183472 -16.940065472703,-48.989839553833 -16.938587549881,-48.977308273315 -16.948358046473,-48.975677490234 -16.948029634664,-48.973875045776 -16.94819384064,-48.97198677063 -16.948604354953,-48.97087097168 -16.948604354953,-48.969841003418 -16.949589585646,-48.970355987549 -16.950246403239,-48.970699310303 -16.951395828506,-48.971643447876 -16.95238104457,-48.972845077515 -16.952627347778,-48.973531723022 -16.953202054011,-48.976707458496 -16.953284154758,-48.977479934692 -16.953940959442,-48.978252410889 -16.953202054011,-48.981857299805 -16.95238104457,-48.982715606689 -16.952134741038,-48.983488082886 -16.952298943428,-48.986749649048 -16.954105160254,-48.987865447998 -16.95558296111,-48.988466262817 -16.957307047421,-48.988380432129 -16.958620626374,-48.991556167603 -16.958045936712,-49.002199172974 -16.952298943428,-49.000825881958 -16.949014868369,-48.996105194092 -16.945648631896,-48.994302749634 -16.943513914128,-48.991212844849 -16.939983366184,-48.990354537964 -16.940640217331))");        
        
        map.zoomToExtent(geom.getBounds());
      //  alert('');
        // Google.v3 uses EPSG:900913 as projection, so we have to
        // transform our coordinates
      //  map.setCenter(new OpenLayers.LonLat(-49.260743, -16.676978).transform(
     //       new OpenLayers.Projection("EPSG:4326"),
     //       map.getProjectionObject()
    //    ), 5);
        
    }