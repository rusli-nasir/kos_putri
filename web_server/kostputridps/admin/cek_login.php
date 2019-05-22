<?php
error_reporting(0);
include "../config/koneksi.php";
$pass=md5($_POST['password']);

$login=mysql_query("SELECT * FROM admin WHERE id_admin='$_POST[username]' AND password='$pass'");
$ketemu=mysql_num_rows($login);
$r=mysql_fetch_array($login);

// Apabila username dan password ditemukan
if ($ketemu > 0){
  session_start();
//  session_register("namauser");
//  session_register("passuser");
//  session_register("leveluser");

  $_SESSION['namauser'] = $r['id_admin'];
  $_SESSION['passuser'] = $r['password'];
  $_SESSION['leveluser']= $r['level'];
  header('location:media.php?module=home');
}
else{
  echo "<link href=../config/adminstyle.css rel=stylesheet type=text/css>";
  echo "<center>Login gagal! username & password tidak benar<br>";
  echo "<a href=index.php><b>ULANGI LAGI</b></a></center>";
}
?>
