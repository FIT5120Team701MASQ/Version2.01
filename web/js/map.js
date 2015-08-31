var map;
var markers = [];

    
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 7,
        center: {lat: -37.49, lng: 145.58}
    });
    
    var startinput = (document.getElementById('start-pac'));
    var endinput = (document.getElementById('end-pac'));

    var autocomplete = new google.maps.places.Autocomplete(startinput);
    autocomplete.bindTo('bounds', map);

    var autocomplete = new google.maps.places.Autocomplete(endinput);
    autocomplete.bindTo('bounds', map);

    var infowindow = new google.maps.InfoWindow();
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
    });

    autocomplete.addListener('place_changed', function () {
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            window.alert("Autocomplete's returned place contains no geometry");
            return;
        }
/*
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);
        }
        marker.setIcon({
            url: place.icon,
            size: new google.maps.Size(71, 71),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(17, 34),
            scaledSize: new google.maps.Size(35, 35)
        });
        marker.setPosition(place.geometry.location);
        marker.setVisible(true);

        var address = '';
        if (place.address_components) {
            address = [
                (place.address_components[0] && place.address_components[0].short_name || ''),
                (place.address_components[1] && place.address_components[1].short_name || ''),
                (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
        }
*/
        infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
        infowindow.open(map, marker);
    });


    //get route
    var directionsService = new google.maps.DirectionsService;

    var directionsDisplay = new google.maps.DirectionsRenderer({
        draggable: true,
        map: map,
        panel: document.getElementById('directionsPanel')
    });

    directionsDisplay.addListener('directions_changed', function () {
        computeTotalDistance(directionsDisplay.getDirections());
    });

    //Get start and end points
    document.getElementById('submit').addEventListener('click', function () {
        addMarker();
        
        displayRoute(document.getElementById('start-pac').value, document.getElementById('end-pac').value,
                directionsService, directionsDisplay); 
        
        //setMapOnAll(map);
    });
    
    
    
    //marker of limits
    
    
}

function addMarker(){
        $.get("/Projectv1.2/DatabaseAccess",{"height":document.getElementById('height').value,"length":document.getElementById('length').value,"width":document.getElementById('width').value},  
            function(resp){
                    $.each(resp, function(i,bridge){
                        var marker = new google.maps.Marker({
                            position:{lat:bridge.lat,lng:bridge.longit},
                            title:"Bridge " + i,
                            icon:"icon/bridge.png"
                        });
                        
                        var contentString = '<div id="content">'+
                        '<div id="siteNotice">'+
                        '</div>'+
                        '<h3 id="firstHeading" class="firstHeading">Bridge: '+ bridge.collName1 +'</h3>'+
                        '<div id="bodyContent">'+
                        '<p><b>Height: </b>' + bridge.minClearance +
                        '<b>Width: </b>' + bridge.width +
                        '<b>Length: </b>' + bridge.length +'</p>'+
                        '</div>'+
                        '</div>';
                
                        var infowindow = new google.maps.InfoWindow({
                            content: contentString
                        });
                        
                        marker.setMap(map);
                        marker.addListener('click', function() {
                            infowindow.open(map, marker);
                        markers.push(marker);
                        });
                });
            });
}

function setMapOnAll(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

function displayRoute(origin, destination, service, display) {
    service.route({
        origin: origin,
        destination: destination,
        //waypoints: [{location: 'Cocklebiddy, WA'}, {location: 'Broken Hill, NSW'}],
        travelMode: google.maps.TravelMode.DRIVING,
        avoidTolls: true
    }, function (response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            display.setDirections(response);
        } else {
            alert('Could not display directions due to: ' + status);
        }
    });
}

function computeTotalDistance(result) {
    var total = 0;
    var myroute = result.routes[0];
    for (var i = 0; i < myroute.legs.length; i++) {
        total += myroute.legs[i].distance.value;
    }
    total = total / 1000;
    document.getElementById('total').innerHTML = total + ' km';
}
