<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$server = "localhost";
$username = "root";
$password = "";
$database = "kosputridps";
$port = 3307;//Default 3306

$gmap_key = 'AIzaSyBgg8CB-TAMqScPX9yEb58iwnwIZ46KuE0';

// Koneksi dan memilih database di server
mysql_connect($server . ':' . $port,$username,$password) or die("Koneksi gagal");
mysql_select_db($database) or die("Database tidak bisa dibuka");
?>
