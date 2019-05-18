<?php
    include "koneksi.php";
    $data['lokasi'] = null;
    /*
    ( 6371 * acos( 
        cos( radians(".$_GET['lat'].") ) * cos( radians( latitude ) ) 
        * cos( radians( longitude ) - radians(".$_GET['long'].") ) + sin( radians(".$_GET['long'].") ) * sin( radians( latitude ) ) ) 
        ) AS distance,
    */
    $multiplier = 112.12; // use 69.0467669 if you want miles
    $distance = $_GET['rad']; // kilometers or miles if 69.0467669
    $query = "SELECT
        a.*,
        (SQRT(POW((latitude - ".$_GET['lat']."), 2) + POW((longitude - ".$_GET['long']."), 2)) * $multiplier) AS distance,
        b.nama_kategori
        FROM
        penginapan AS a
        INNER JOIN katpenginapan AS b ON a.id_kategori_penginapan = b.id_kategori_penginapan
        WHERE POW((latitude - ".$_GET['lat']."), 2) + POW((longitude - ".$_GET['long']."), 2) < POW(($distance / $multiplier), 2)";
        
    //SELECT * from gereja where id_kategori_gereja='".$_GET['idkatgereja']."' and id_aliran_gereja='".$_GET['idalirangereja']."'";
    $blog = mysql_query($query);
    if($blog){
        while($b=mysql_fetch_array($blog)){
            $data['lokasi'][] = [
                'id_penginapan'          	=> $b['id_penginapan'],
                'id_kategori_penginapan'    => $b['id_kategori_penginapan'],
                'nama_kategori'       		=> $b['nama_kategori'],
                'nama'          			=> $b['nama'],
                'fasilitas'          		=> $b['fasilitas'],
                'harga'          			=> $b['harga'],
                'status'          			=> $b['status'],
                'alamat'                => $b['alamat'],
                'latitude'              => $b['latitude'],
                'longitude'             => $b['longitude'],
                'telepon'               => $b['telepon'],
                'gambar'                => $b['gambar'],                
                'distance'                => $b['distance'],                
            ];            
        }
        
    }else{        
        $data['lokasi'][] = [];
    }
    
    echo json_encode($data);
    mysql_close();     