<?php
    include "koneksi.php";
    $query = "SELECT
        a.*,
        b.nama_kategori
        FROM
        penginapan AS a
        INNER JOIN katpenginapan AS b ON a.id_kategori_penginapan = b.id_kategori_penginapan
        where a.id_kategori_penginapan=".$_GET['idkatpenginapan'];
        //where a.id_kategori_gereja='".$_GET['idkatgereja']."' and a.id_aliran_gereja='".$_GET['idalirangereja']."'";
        
    //SELECT * from gereja where id_kategori_gereja='".$_GET['idkatgereja']."' and id_aliran_gereja='".$_GET['idalirangereja']."'";
    //echo $query;
    $blog = mysql_query($query);
    //print_r('<pre>');
    //print_r($blog);
    //print_r('</pre>');
    if($blog){
        
        while($b=mysql_fetch_assoc($blog)){            
            $data['penginapan'][] = [
                'id_penginapan'         	=> $b['id_penginapan'],
                'id_kategori_penginapan'    => $b['id_kategori_penginapan'],
                'nama_kategori'       		=> $b['nama_kategori'],
                'nama'           			=> $b['nama'],
                'fasilitas'           		=> $b['fasilitas'],
                'harga'           			=> $b['harga'],
                'status'           			=> $b['status'],
                'alamat'                => $b['alamat'],
                'latitude'              => $b['latitude'],
                'longitude'             => $b['longitude'],
                'telepon'               => $b['telepon'],
                'gambar'                => $b['gambar'],                
            ];            
        }        
    }else{
        $data = [];
    }
    echo json_encode($data);
    mysql_close();     