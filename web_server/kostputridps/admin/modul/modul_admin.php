<?php
error_reporting(E_ALL ^ (E_NOTICE | E_WARNING));
switch($_GET[act]){
  // Tampil Admin
  default:
    echo "<h2>Admin</h2>
          <input type=button value='Tambah Admin' onclick=location.href='?module=admin&act=tambahadmin'>
          <table>
          <tr><th>no</th><th>username</th><th>nama lengkap</th><th>email</th><th>aksi</th></tr>";
    $tampil=mysql_query("SELECT * FROM admin ORDER BY id_admin");
    $no=1;
    while ($r=mysql_fetch_array($tampil)){
      echo "<tr><td>$no</td>
             <td>$r[id_admin]</td>
             <td>$r[nama_lengkap]</td>
		         <td><a href=mailto:$r[email]>$r[email]</a></td>
             <td><a href=?module=admin&act=editadmin&id=$r[id_admin]>Edit</a> |
	               <a href=./aksi.php?module=admin&act=hapus&id=$r[id_admin]>Hapus</a>
             </td></tr>";
      $no++;
    }
    echo "</table>";
    break;

  case "tambahadmin":
    echo "<h2>Tambah Admin</h2>
          <form method=POST action='./aksi.php?module=admin&act=input'>
          <table>
          <tr><td>Username</td>     <td> : <input type=text name='id_admin'></td></tr>
          <tr><td>Password</td>     <td> : <input type=text name='password'></td></tr>
          <tr><td>Nama Lengkap</td> <td> : <input type=text name='nama_lengkap' size=30></td></tr>
          <tr><td>E-mail</td>       <td> : <input type=text name='email' size=30></td></tr>
		  <tr><td>Level</td>		<td> : <input type=text name='level' size=20></td></tr>
          <tr><td colspan=2><input type=submit value=Simpan>
                            <input type=button value=Batal onclick=self.history.back()></td></tr>
          </table></form><br><br>";
    break;

  case "editadmin":
    $edit=mysql_query("SELECT * FROM admin WHERE id_admin='$_GET[id]'");
    $r=mysql_fetch_array($edit);

    echo "<h2>Edit Admin</h2>
          <form method=POST action=./aksi.php?module=admin&act=update>
          <input type=hidden name=id value='$r[id_admin]'>
          <table>
          <tr><td>Username</td>     <td> : <input type=text name='id_admin' value='$r[id_admin]'></td></tr>
          <tr><td>Password</td>     <td> : <input type=text name='password'> *) </td></tr>
          <tr><td>Nama Lengkap</td> <td> : <input type=text name='nama_lengkap' size=30  value='$r[nama_lengkap]'></td></tr>
          <tr><td>E-mail</td>       <td> : <input type=text name='email' size=30 value='$r[email]'></td></tr>
		  <tr><td>Level</td>		<td> : <input type=text name='level' size=20 value='$r[level]'></td></tr>
          <tr><td colspan=2>*) Apabila password tidak diubah, dikosongkan saja.</td></tr>
          <tr><td colspan=2><input type=submit value=Update>
                            <input type=button value=Batal onclick=self.history.back()></td></tr>
          </table></form>";
    break;
}
?>
