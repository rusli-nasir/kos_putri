<?php
session_start();
include "../config/koneksi.php";
include "../config/library.php";

$module=$_GET['module'];
$act=$_GET['act'];

// Menghapus data
if (isset($module) AND $act=='hapus'){
  mysql_query("DELETE FROM ".$module." WHERE id_".$module."='$_GET[id]'");
  header('location:media.php?module='.$module);
}

// Input admin
elseif ($module=='admin' AND $act=='input'){
  $pass=md5($_POST['password']);
  mysql_query("INSERT INTO admin(id_admin,
                                password,
                                nama_lengkap,
                                email, level)
	                       VALUES('$_POST[id_admin]',
                                '$pass',
                                '$_POST[nama_lengkap]',
                                '$_POST[email]',
								'$_POST[level]')");
  header('location:media.php?module='.$module);
}

// Update user admin
elseif ($module=='admin' AND $act=='update'){
  // Apabila password tidak diubah
  if (empty($_POST['password'])) {
    mysql_query("UPDATE admin SET id_admin      = '$_POST[id_admin]',
                                 nama_lengkap = '$_POST[nama_lengkap]',
                                 email        = '$_POST[email]',
								 level		  = '$_POST[level]'
                           WHERE id_admin      = '$_POST[id]'");
  }
  // Apabila password diubah
  else{
    $pass=md5($_POST['password']);
    mysql_query("UPDATE user SET id_admin      = '$_POST[id_admin]',
                                 password     = '$pass',
                                 nama_lengkap = '$_POST[nama_lengkap]',
                                 email        = '$_POST[email]',
								 level		  = '$_POST[level]'
                           WHERE id_admin      = '$_POST[id]'");
  }
  header('location:media.php?module='.$module);
}


// Input modul
elseif ($module=='modul' AND $act=='input'){
  mysql_query("INSERT INTO modul(nama_modul,
                                 link,
                                 aktif,
                                 urutan)
	                       VALUES('$_POST[nama_modul]',
                                '$_POST[link]',
                                '$_POST[aktif]',
                                '$_POST[urutan]')");
  header('location:media.php?module='.$module);
}

// Update modul
elseif ($module=='modul' AND $act=='update'){
  mysql_query("UPDATE modul SET nama_modul = '$_POST[nama_modul]',
                                link       = '$_POST[link]',
                                aktif      = '$_POST[aktif]',
                                urutan     = '$_POST[urutan]'
                          WHERE id_modul   = '$_POST[id]'");
  header('location:media.php?module='.$module);
}

// Input KATEGORI PENGINAPAN
elseif ($module=='katpenginapan' AND $act=='input'){


  mysql_query("INSERT INTO katpenginapan(nama_kategori,
                                     keterangan)
					                     VALUES('$_POST[nmkategori]',
												'$_POST[ket]')");
  header('location:media.php?module='.$module);
}
// Update KATEGORI PENGINAPAN
elseif ($module=='katpenginapan' AND $act=='update'){


  mysql_query("UPDATE katpenginapan SET  nama_kategori     = '$_POST[nmkategori]',
                                     keterangan        = '$_POST[ket]'
									 WHERE id_kategori_penginapan     = '$_POST[id]'");
  header('location:media.php?module='.$module);
}

// Input penginapan
elseif ($module=='penginapan' AND $act=='input'){
  $lokasi_file = $_FILES['fupload']['tmp_name'];
  $fileInfo = pathinfo($_FILES['fupload']['name']);
  //$nama_file   = $_FILES['fupload']['name'];
  $nama_file   = 'penginapan_' . time() . '.' .$fileInfo['extension'];   


  // Apabila ada gambar yang diupload
  if (!empty($lokasi_file)){
    move_uploaded_file($lokasi_file,"image_upload/$nama_file");
    $icon=1;
    //if($_POST['aliran_gereja']==9){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==13){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==17){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==21){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==25){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==33){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==41){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==45){
    //  $icon=1;
    //}
    //else{
    //  $icon=1;
    //}
    mysql_query("INSERT INTO penginapan(nama,
    								  fasilitas,
    								  harga,
    								  status,
                                      alamat,
									  id_kategori_penginapan,
									  id_icon,
									  latitude,
									  longitude,
									  telepon,
									  gambar )
					                     VALUES('$_POST[nama]',
					                     		'$_POST[fasilitas]',
					                     		'$_POST[harga]',
					                     		'$_POST[status]',
												'$_POST[alamat]',
												'$_POST[kategori]',
												'$icon',
												'$_POST[lati]',
												'$_POST[long]',
												'$_POST[telepon]',
												'$nama_file')");
  }
  else{
    // echo"$_POST['aliran_gereja'] <p>";
    $icon=1;
    //if($_POST['aliran_gereja']==9){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==13){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==17){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==21){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==25){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==33){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==41){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==45){
    //  $icon=1;
    //}
    //
    //else{
    //  $icon=1;
    //}
    //echo $icon;
    mysql_query("INSERT INTO penginapan(nama,
    								  fasilitas,
    								  harga,
    								  status,
                                      alamat,
									  id_kategori_gereja,
									  id_icon,
									  latitude,
									  longitude,
									  telepon)
                            VALUES('$_POST[nama]',
                            					'$_POST[fasilitas]',
                            					'$_POST[harga]',
                            					'$_POST[status]',
												'$_POST[alamat]',
												'$_POST[kategori]',
												'$icon',
												'$_POST[lati]',
												'$_POST[long]',
												'$_POST[telepon]')");
  }  
  header('location:media.php?module='.$module);
}
// Update Penginapan
elseif ($module=='penginapan' AND $act=='update'){
  $lokasi_file = $_FILES['fupload']['tmp_name'];
  $fileInfo = pathinfo($_FILES['fupload']['name']);
  //$nama_file   = $_FILES['fupload']['name'];
  $nama_file   = 'penginapan_' . time() . '.' .$fileInfo['extension'];   

  // Apabila gambar tidak diganti
  if (empty($lokasi_file)){
    $telepon = mysql_real_escape_string($_POST[telepon]);

    $icon=1;
    //if($_POST['aliran_gereja']==9){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==13){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==17){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==21){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==25){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==33){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==41){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==45){
    //  $icon=1;
    //}
    //else{
    //  $icon=1;
    //}
    $query = "UPDATE penginapan SET nama = '$_POST[nmpenginapan]',
    								  fasilitas = '$_POST[fasilitas]',
    								  harga = '$_POST[harga]',
    								  status = '$_POST[status]',
                                      alamat = '$_POST[alamat]',
									  id_kategori_penginapan = '$_POST[kategori]',
									  id_icon = '$icon',
									  latitude = '$_POST[lati]',
									  longitude = '$_POST[long]',
									  telepon = '$telepon'
                             WHERE id_penginapan = '$_POST[id]'";
    //	 echo $query;
    mysql_query($query) or die(mysql_error());
  }else{
    move_uploaded_file($lokasi_file,"image_upload/$nama_file");
    $icon=1;
    //if($_POST['aliran_gereja']==9){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==13){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==17){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==21){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==25){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==33){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==41){
    //  $icon=1;
    //}
    //elseif($_POST['aliran_gereja']==45){
    //  $icon=1;
    //}
    //else{
    //  $icon=1;
    //}
    mysql_query("UPDATE penginapan SET nama = '$_POST[nmpenginapan]',
    								  fasilitas = '$_POST[fasilitas]',
    								  harga = '$_POST[harga]',
    								  status = '$_POST[status]',
                                      alamat = '$_POST[alamat]',
									  id_kategori_penginapan = '$_POST[katpenginapan]',
									  id_icon = '$icon',
									  latitude = '$_POST[lati]',
									  longitude = '$_POST[long]',
									  telepon = '$_POST[telepon]',
                                      gambar    = '$nama_file'
                             WHERE id_penginapan = '$_POST[id]'");
  }
  header('location:media.php?module='.$module);
}

?>
