<?php
include "koneksi.php";


$sqlString = "select nama, fasilitas, harga, status, alamat, telepon from penginapan where id_penginapan = '".$_GET['idpenginapan']."'";

$rs = mysql_query($sqlString);


if($rs){
    while($objRs = mysql_fetch_assoc($rs)){
        $output[] = $objRs;


    }
    echo json_encode($output);
}

mysql_close();
?>