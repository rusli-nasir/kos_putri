<?php
include "../../config/koneksi.php";

echo "<select name=penginapan>";
$sql2=mysql_query("SELECT * FROM penginapan WHERE id_kategori_penginapan='$_GET[kode]'");
while ($row=mysql_fetch_array($sql2)){
    echo "<option value=$row[id_penginapan]>$row[nama]</option>";
}
echo "</select>";
?>
