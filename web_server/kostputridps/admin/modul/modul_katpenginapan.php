<?php
error_reporting(E_ALL ^ (E_NOTICE | E_WARNING));
switch($_GET['act']){
  // Tampil KATEGORI PENGINAPAN
  default:
    echo "<h2>Kategori Penginapan</h2>
          <input type=button value='Tambah Kategori Penginapan' onclick=location.href='?module=katpenginapan&act=tambahkategoripenginapan'>
          <table>
          <tr><th>no</th><th>Nama Kategori Penginapan</th><th>Keterangan</th><th>aksi</th></tr>";
    if ($_SESSION['leveluser']=='admin'){
      $tampil=mysql_query("SELECT * FROM katpenginapan ORDER BY id_kategori_penginapan DESC");
    }

    $no=1;
    while ($r=mysql_fetch_array($tampil)){
      $tanggal=tgl_indo($r[tanggal]);
      echo "<tr><td>$no</td>
                <td>$r[nama_kategori]</td>
				<td>$r[keterangan]</td>
                <td><a href=?module=katpenginapan&act=editkategoripenginapan&id=$r[id_kategori_penginapan]>Edit</a> |
	                  <a href=./aksi.php?module=katpenginapan&act=hapus&id=$r[id_kategori_penginapan]>Hapus</a>
		        </tr>";
      $no++;
    }
    echo "</table>";
    break;

  case "tambahkategoripenginapan":
    echo "<h2>Tambah Kategori Penginapan</h2>
          <form method=POST action='./aksi.php?module=katpenginapan&act=input'>
          <table>
          <tr><td>Nama Kategori</td>      <td> : <input type=text name='nmkategori' size=60></td></tr>
		  <tr><td>Keterangan</td>         <td> : <input type=text name='ket' size=60></td></tr>

          <tr><td colspan=2><input type=submit value=Simpan>
                            <input type=button value=Batal onclick=self.history.back()></td></tr>
          </table></form>";
    break;

  case "editkategoripenginapan":
    $edit = mysql_query("SELECT * FROM katpenginapan WHERE id_kategori_penginapan='$_GET[id]'");
    $r    = mysql_fetch_array($edit);

    echo "<h2>Edit Kategori Penginapan</h2>
          <form method=POST action=./aksi.php?module=katpenginapan&act=update>
          <input type=hidden name=id value=$r[id_kategori_penginapan]>
          <table>
          <tr><td>Nama Kategori</td> <td> : <input type=text name='nmkategori' size=60 value='$r[nama_kategori]'></td></tr>
		  <tr><td>Keterangan</td>    <td> : <input type=text name='ket' size=60 value='$r[keterangan]'></td></tr>

          <tr><td colspan=2><input type=submit value=Update>
                            <input type=button value=Batal onclick=self.history.back()></td></tr>
          </table></form>";
    break;
}
?>
