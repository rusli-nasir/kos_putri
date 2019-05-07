<?php
include "../config/koneksi.php";

if ($_SESSION['leveluser']=='admin'){
  $sql=mysql_query("select * from modul where aktif='Y' order by urutan");
}
else if ($_SESSION['leveluser']=='pemilik'){
  $sql=mysql_query("select * from modul where urutan='1'");
}
while ($data=mysql_fetch_array($sql)){
  echo "<li><a href='$data[link]'>&#187; $data[nama_modul]</a></li>";
}
?>
