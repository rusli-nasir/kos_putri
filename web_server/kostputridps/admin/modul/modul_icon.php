<?php
switch($_GET[act]){
  // Tampil Icon
  default:
    echo "<h2>Icon</h2>
          <input type=button value='Tambah Icon' onclick=location.href='?module=icon&act=tambahicon'>
          <table>
          <tr><th>no</th><th>Nama Icon</th><th>Keterangan</th><th><th>aksi</th></tr>";
    $tampil=mysql_query("SELECT * FROM icon ORDER BY id_icon DESC");
    $no=1;
    while ($r=mysql_fetch_array($tampil)){
      $tgl=tgl_indo($r[tgl_posting]);
      echo "<tr><td>$no</td>
                <td>$r[nama_icon]</td>
                <td>$r[keterangan]</td>
                <td><a href=?module=icon&act=editicon&id=$r[id_icon]>Edit</a> |
	                  <a href=./aksi.php?module=icon&act=hapus&id=$r[id_icon]>Hapus</a>
		        </tr>";
      $no++;
    }
    echo "</table>";
    break;

  case "tambahicon":
    echo "<h2>Tambah icon</h2>
          <form method=POST action='./aksi.php?module=icon&act=input' enctype='multipart/form-data'>
          <table>
          <tr><td>Nama_icon</td><td>  : <input type=text name='nmicon' size=30></td></tr>
		   <tr><td>Keterangan</td><td>  : <input type=text name='ket' size=30></td></tr>
          <tr><td>Gambar</td>    <td> : <input type=file name='fupload' size=40></td></tr>
		  <tr><td colspan=2><input type=submit value=Simpan>
                            <input type=button value=Batal onclick=self.history.back()></td></tr>
          </table></form>";
    break;

  case "editicon":
    $edit = mysql_query("SELECT * FROM icon WHERE id_icon='$_GET[id]'");
    $r    = mysql_fetch_array($edit);

    echo "<h2>Edit Icon</h2>
          <form method=POST enctype='multipart/form-data' action=./aksi.php?module=icon&act=update>
          <input type=hidden name=id value=$r[id_icon]>
          <table>
          <tr><td>Nama Icon</td><td>     : <input type=text name='nmicon' size=30 value='$r[nama_icon]'></td></tr>
			<tr><td>Keterangan</td><td>     : <input type=text name='ket' size=30 value='$r[keterangan]'></td></tr>
          <tr><td>Gambar</td><td>    : <img src='image_upload/$r[gambar]'></td></tr>
          <tr><td>Ganti Gbr</td><td> : <input type=file name='fupload' size=30> *)</td></tr>
          <tr><td colspan=2>*) Apabila gambar tidak diubah, dikosongkan saja.</td></tr>
          <tr><td colspan=2><input type=submit value=Update>
                            <input type=button value=Batal onclick=self.history.back()></td></tr>
          </table></form>";
    break;
}
?>
