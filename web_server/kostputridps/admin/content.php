<?php
include "../config/koneksi.php";
include "../config/library.php";
include "../config/fungsi_indotgl.php";
include "../config/fungsi_combobox.php";
include "../config/class_paging.php";

// Bagian Home
if ($_GET['module']=='home'){
  echo "<h2>Selamat Datang</h2>
          <p>Hai <b>$_SESSION[namauser]</b>, silahkan klik menu pilihan yang berada
          di sebelah kiri untuk mengelola content website. </p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p align=right>Login Hari ini: ";
  echo tgl_indo(date("Y m d"));
  echo " | ";
  echo date("H:i:s");
  echo "</p>";
}

// Bagian Admin
elseif ($_GET['module']=='admin'){
  include "modul/modul_admin.php";
}

// Bagian Modul
elseif ($_GET['module']=='modul'){
  include "modul/modul_modul.php";
}

// Bagian Menu
elseif ($_GET['module']=='menu'){
  include "modul/modul_menu.php";
}

// Bagian Kategori Penginapan
elseif ($_GET['module']=='katpenginapan'){
  include "modul/modul_katpenginapan.php";
}
// Bagian Penginapan
elseif ($_GET['module']=='penginapan'){
  include "modul/modul_penginapan.php";
}

// Bagian  Icon
elseif ($_GET['module']=='icon'){
  include "modul/modul_icon.php";
}
// Apabila modul tidak ditemukan
else{
  echo "<p><b>MODUL BELUM ADA</b></p>";
}
?>
