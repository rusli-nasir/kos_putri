<style>
    #map {
        margin: 0;
        padding: 0;
        height: 400px;
        width: 400px;
    }
    .controls {
        margin-top: 10px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
    }

    #pac-input {
        background-color: #fff;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        margin-left: 12px;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;
        width: 250px;
    }

    #pac-input:focus {
        border-color: #4d90fe;
    }

    .pac-container {
        font-family: Roboto;
    }

    #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
    }

    #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
    }
    #target {
        width: 345px;
    }
</style>
<script type='text/javascript'>
    var latitude;
    var longitude;

    var xmlhttp = createRequestObject();

    function createRequestObject() {
        var ro;
        var browser = navigator.appName;
        if(browser == "Microsoft Internet Explorer"){
            ro = new ActiveXObject("Microsoft.XMLHTTP");
        }else{
            ro = new XMLHttpRequest();
        }
        return ro;
    }

    function dinamis(combobox)
    {
        var kode = combobox.value;
        if (!kode) return;
        xmlhttp.open('get', 'modul/getdata.php?kode='+kode, true);
        xmlhttp.onreadystatechange = function() {
            if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200))
            {
                document.getElementById("tampilaliran").innerHTML = xmlhttp.responseText;
            }
            return false;
        }
        xmlhttp.send(null);
    }


</script>
    <?php
    $id_user = $_SESSION['namauser'];
error_reporting(E_ALL ^ (E_NOTICE | E_WARNING));
switch($_GET['act']){
    // Tampil Penginapan
    default:
        echo "<h2>Penginapan</h2>
          <input type=button value='Tambah Penginapan' onclick=location.href='?module=penginapan&act=tambahpenginapan'>
          <table>
           <tr>
           <th>no</th>
           <th>Nama Penginapan</th>           
           <th>Pemilik</th>           
           <th>Latitude</th>
           <th>Longitude</th>
           <th>aksi</th>
           </tr>";

        $p      = new Paging;
        $batas  = 10;
        $posisi = $p->cariPosisi($batas);
        $query = '';

        if ($_SESSION['leveluser']=='admin'){
            $query = "SELECT * FROM penginapan ORDER BY id_penginapan DESC limit {$posisi},{$batas}";
        } if ($_SESSION['leveluser']=='pemilik'){
            $query = "SELECT * FROM penginapan WHERE id_pemilik = '{$id_user}' ORDER BY id_penginapan DESC limit {$posisi},{$batas}";

        }
        $tampil = mysql_query($query);

        $no = $posisi+1;
        while($r=mysql_fetch_array($tampil)){
            $tgl_posting=tgl_indo($r[tanggal]);
            echo "<tr><td>$no</td>
                <td>$r[nama]</td>
                <td>$r[id_pemilik]</td>
				<td>$r[latitude]</td>
				<td>$r[longitude]</td>
                <td><a href=?module=penginapan&act=editpenginapan&id=$r[id_penginapan]>Edit</a> |
		                <a href=./aksi.php?module=penginapan&act=hapus&id=$r[id_penginapan]>Hapus</a></td>
		        </tr>";
            $no++;
        }
        echo "</table>";

        if ($_SESSION['leveluser']=='admin'){
            $jmldata = mysql_num_rows(mysql_query("SELECT * FROM penginapan"));
        }
        else{
            $jmldata = mysql_num_rows(mysql_query("SELECT * FROM penginapan WHERE id_pemilik = '{$id_user}'"));
        }
        $jmlhalaman  = $p->jumlahHalaman($jmldata, $batas);
        $linkHalaman = $p->navHalaman($_GET['halaman'], $jmlhalaman);

        echo "<div id=paging>$linkHalaman</div><br>";
        break;

    case "tambahpenginapan":
        $optkatpenginapan;
//        $optaliran;
        $tampil=mysql_query("SELECT * FROM katpenginapan");
        while($r=mysql_fetch_array($tampil)){
            $optkatpenginapan .= "<option value=$r[id_kategori_penginapan]>$r[nama_kategori]</option>";
        }
        $optPemilik = '';
    if ($_SESSION['leveluser']=='admin'){
        $dataAdmin=mysql_query("SELECT * FROM admin where `level` = 'pemilik'");
        while($r=mysql_fetch_array($dataAdmin)){
            $optPemilik .= "<option value=$r[id_admin]>$r[nama_lengkap]</option>";
        }
    }


//        $tampil=mysql_query("SELECT * FROM aliran_gereja");
//        while($r=mysql_fetch_array($tampil)){
//            $optaliran .= "<option value=$r[id_aliran_gereja]>$r[nama_kategori]</option>";
//        }


        ?>
        <h2>Tambah Penginapan</h2>
        <input id="pac-input" class="controls" type="text" placeholder="Cari Alamat">
        <div id="map"></div> 
        <form method=POST action='./aksi.php?module=penginapan&act=input' enctype='multipart/form-data'>
            <table>
                <tr>
                    <td>Nama Pemilik</td>
                    <td> : <input type=text name='nama' size=60></td>
                </tr>
                <tr>
                    <td>Fasilitas</td>
                    <td> : <input type=text name='fasilitas' size=60></td>
                </tr>
                <tr>
                    <td>Harga</td>
                    <td> : <input type=text name='harga' size=60></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td> : <input type=text name='status' size=60></td>
                </tr>
                <tr>
                    <td>Alamat</td>
                    <td> : <input type=text name='alamat' size=60></td>
                </tr>
                <tr>
                    <td>Kategori</td>
                    <td> : <select name='kategori' onChange='javascript:dinamis(this)'>
                            <option value=0 selected>- Pilih Kategori Penginapan -</option>
                            <?= $optkatpenginapan?>
                        </select>
                    </td>
                </tr>
                <?php
                if ($_SESSION['leveluser']=='admin'){
                    ?>
                    <tr>
                        <td>Pemilik</td>
                        <td> : <select name='id_pemilik'>
                                <option value=0 selected>- Pilih Pemilik Penginapan -</option>
                                <?= $optPemilik?>
                            </select>
                        </td>
                    </tr>
                    <?php
                }else{
                    ?>
                    <input type="hidden" name="id_pemilik" value="<?= $id_user?>">
                    <?php
                }
                ?>
                <tr>
                    <td>Latitude</td>
                    <td> : <input type=text id="lati" name="lati" size=60></td>
                </tr>
                <tr>
                    <td>Longitude</td>
                    <td> : <input type=text id="long" name="long" size=60></td>
                </tr>
                <tr>
                    <td>Telepon</td>
                    <td> : <input type=text name="telepon" size=60></td>
                </tr>
                <tr>
                    <td>Gambar</td>
                    <td> : <input type=file name="fupload" size=40></td>
                </tr>
                <tr>
                    <td colspan=2><input type=submit value=Simpan>
                        <input type=button value=Batal onclick=self.history.back()>
                    </td>
                </tr>
            </table>
        </form>
        <?php
        break;

    case "editpenginapan":
        $qry = "SELECT * FROM penginapan
               WHERE id_penginapan='$_GET[id]'";
		//echo $qry;
        $edit = mysql_query($qry);
        $r    = mysql_fetch_array($edit);

        $QrylistKetegory = mysql_query("SELECT * FROM katpenginapan ORDER BY nama_kategori");
        $listKetegory;
        while($w = mysql_fetch_array($QrylistKetegory)){
            if ($r['id_kategori_penginapan']==$w['id_kategori_penginapan']){
                $listKetegory .= "<option value='$w[id_kategori_penginapan]' selected>$w[nama_kategori]</option>";
            }
            else{
                $listKetegory .= "<option value='$w[id_kategori_penginapan]'>$w[nama_kategori]</option>";
            }
        }

        $optPemilik = '';
        if ($_SESSION['leveluser']=='admin'){
            $dataAdmin=mysql_query("SELECT * FROM admin where `level` = 'pemilik'");
            while($x=mysql_fetch_array($dataAdmin)){
                if ($r['id_pemilik']==$x['id_admin']){
                    $optPemilik .= "<option value=$x[id_admin] selected>$x[nama_lengkap]</option>";
                }
                else{
                    $optPemilik .= "<option value=$x[id_admin]>$x[nama_lengkap]</option>";
                }
            }
        }

        ?>
        <script>
            latitude = <?= $r['latitude']?>;
            longitude = <?= $r['longitude']?>;
        </script>
        <h2>Edit Penginapan</h2>
        <input id="pac-input" class="controls" type="text" placeholder="Cari Alamat">
        <div id="map"></div>
        <form method=POST enctype='multipart/form-data' action=./aksi.php?module=penginapan&act=update>
            <input type=hidden name=id value="<?= $r[id_penginapan]?>">
            <table>
                <tr>
                    <td>Nama Pemilik</td>
                    <td> : <input type=text name='nmpenginapan' size=40 value='<?= $r[nama]?>'></td>
                </tr>
                <tr>
                  <td>Fasilitas</td>
                  <td> : <input type=text name='fasilitas' size=40 value='<?= $r[fasilitas]?>'></td>
                </tr>
                <tr>
                  <td>Harga</td>
                  <td> : <input type=text name='harga' size=40 value='<?= $r[harga]?>'></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td> : <input type=text name='status' size=60></td>
                </tr>
                <tr>
                    <td>Alamat</td>
                    <td> : <input type=text name='alamat' size=60 value='<?= $r[alamat]?>'></td>
                </tr>
                <tr>
                    <td>Kategori</td>
                    <td> : <select name='kategori' onChange='javascript:dinamis(this)'><?= $listKetegory?></select></td>
                </tr>
                <?php
                if ($_SESSION['leveluser']=='admin'){
                    ?>
                    <tr>
                        <td>Pemilik</td>
                        <td> : <select name='id_pemilik'>
                                <option value=0 selected>- Pilih Pemilik Penginapan -</option>
                                <?= $optPemilik?>
                            </select>
                        </td>
                    </tr>
                    <?php
                }
                ?>
                <tr>
                    <td>Latitude</td>
                    <td>          : <input type=text id="lati" name='lati' size=60 value='<?= $r[latitude]?>'></td></tr>
                <tr>
                    <td>Longitude</td>
                    <td>         : <input type=text id="long" name='long' size=60 value='<?= $r[longitude]?>'></td></tr>
                <tr>
                    <td>Telepon</td>
                    <td>		: <input type=text name='telepon' size=60 value='<?= $r[telepon]?>'></td></tr>
                <tr>
                    <td>Gambar</td>
                    <td> : <img src='image_upload/<?= $r[gambar]?>'></td></tr>
                <tr>
                    <td>Ganti Gbr</td>
                    <td> : <input type=file name='fupload' size=30> *)</td></tr>
                <tr>
                    <td colspan=2>*) Apabila gambar tidak diubah, dikosongkan saja.</td>
                </tr>
                <tr>
                    <td colspan=2>
                        <input type=submit value=Update>
                        <input type=button value=Batal onclick=self.history.back()>
                    </td>
                </tr>
            </table>
        </form>
        <?php

        break;
}
?>
<?php
if($_GET['act'] == 'tambahpenginapan' || $_GET['act'] == 'editpenginapan') {
    ?>
    <script type='text/javascript'>
        var map;
        var markers = [];
        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 18,
                center: {
                    lat: (latitude)? latitude:-8.6852507,
                    lng: (longitude)?longitude: 115.23022139999998
                },
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });


            // Create the search box and link it to the UI element.
            var input = document.getElementById('pac-input');
            var searchBox = new google.maps.places.SearchBox(input);
            map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

            // Bias the SearchBox results towards current map's viewport.
            map.addListener('bounds_changed', function() {
                searchBox.setBounds(map.getBounds());
            });
            map.addListener('click', function(e) {
                //console.log(e);
                placeMarkerAndPanTo(e.latLng);
            });
            // Listen for the event fired when the user selects a prediction and retrieve
            // more details for that place.
            searchBox.addListener('places_changed', function()
            {
                setMapOnAll(null);
                var places = searchBox.getPlaces();

                if (places.length == 0) {
                    return;
                }
                // Clear out the old markers.
                markers.forEach(function(marker) {
                    marker.setMap(null);
                });
                markers = [];

                // For each place, get the icon, name and location.
                var bounds = new google.maps.LatLngBounds();
                places.forEach(function(place) {
                    var icon = {
                        url: place.icon,
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(25, 25)
                    };

                    // Create a marker for each place.
                    markers.push(new google.maps.Marker({
                        map: map,
                        icon: icon,
                        title: place.name,
                        draggable:true,
                        animation: google.maps.Animation.DROP,
                        position: place.geometry.location
                    }));

                    document.getElementById('lati').value = place.geometry.location.lat();
                    document.getElementById('long').value = place.geometry.location.lng();
                    google.maps.event.addListener(markers[0], 'dragend', function(event)
                    {
                        geocodePosition(event);
                    });

                    if (place.geometry.viewport) {
                        // Only geocodes have viewport.
                        bounds.union(place.geometry.viewport);
                    } else {
                        bounds.extend(place.geometry.location);
                    }
                });
                map.fitBounds(bounds);
            });


            function geocodePosition(pos)
            {
                document.getElementById('lati').value = pos.latLng.lat();
                document.getElementById('long').value = pos.latLng.lng();
//                geocoder = new google.maps.Geocoder();
//                geocoder.geocode
//                ({
//                        latLng: pos
//                    },
//                    function(results, status)
//                    {
//                        if (status == google.maps.GeocoderStatus.OK)
//                        {
//                            $("#mapSearchInput").val(results[0].formatted_address);
//                            $("#mapErrorMsg").hide(100);
//                        }
//                        else
//                        {
//                            $("#mapErrorMsg").html('Cannot determine address at this location.'+status).show(100);
//                        }
//                    }
//                );
            }
            function placeMarkerAndPanTo(latLng) {
                latitude = latLng.lat();
                longitude = latLng.lng();
                if(markers[0]){
                    markers[0].setPosition(latLng);
                }else{
//                    var icon = {
//                        url: place.icon,
//                        size: new google.maps.Size(71, 71),
//                        origin: new google.maps.Point(0, 0),
//                        anchor: new google.maps.Point(17, 34),
//                        scaledSize: new google.maps.Size(25, 25)
//                    };
                    markers[0] = new google.maps.Marker({
                        position: latLng,
                        map: map,
                        //draggable: true,
//                        icon: icon
                    });
                    map.addListener(markers[0],'drag',function(event) {
                        latitude = event.latLng.lat();
                        longitude = event.latLng.lng();
                    });

                    map.addListener(markers[0],'click',function(event) {
                        console.log(event);
                    });

                    map.addListener(markers[0],'dragend',function(event) {
                        latitude = event.latLng.lat();
                        longitude = event.latLng.lng();
                    });
                }

                map.panTo(latLng);
                document.getElementById('lati').value = latitude;
                document.getElementById('long').value = longitude;
            }

            // Sets the map on all markers in the array.
            function setMapOnAll(map) {
                for (var i = 0; i < markers.length; i++) {
                    markers[i].setMap(map);
                }
            }

            //show default marker from database
            if(latitude && longitude){
                markers.push(new google.maps.Marker({
                    position: {
                        lat: latitude,
                        lng: longitude
                    },
                    draggable:true,
                    map: map
                }));
                google.maps.event.addListener(markers[0], 'dragend', function(event)
                {
                    geocodePosition(event);
                });
            }
        }

    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?v=quarterly&key=<?php echo $gmap_key?>&libraries=places&callback=initMap"></script>
<?php }?>
