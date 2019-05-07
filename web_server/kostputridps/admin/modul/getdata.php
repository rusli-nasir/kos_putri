<?php
include "../../config/koneksi.php";

echo "<select name=aliran_gereja>";
$sql2=mysql_query("SELECT * FROM aliran_gereja WHERE id_kategori_gereja='$_GET[kode]'");
while ($row=mysql_fetch_array($sql2)){
    $icon=0;
    if ($row[nama_aliran]=='Hotel'){
        echo $icon=1;
    }elseif($row[nama_aliran]=='Lokasi Pertandingan'){
        echo $icon=2;
    }elseif($row[nama_aliran]=='Perumahan'){
        echo $icon=3;
    }elseif($row[nama_aliran]=='Asrama'){
        echo $icon=4;
    }
    echo "<option value=$row[id_aliran_gereja]>$row[nama_aliran]</option>";
}
echo "</select>";
?>