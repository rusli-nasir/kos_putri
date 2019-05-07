<?php
$server = "localhost";
$username = "root";
$password = "";
$databasename = "kosputridps";
$connection = mysql_connect($server, $username, $password) or die("Kesalahan Koneksi � !!");
mysql_select_db($databasename, $connection) or die("Databasenya Error");

?>