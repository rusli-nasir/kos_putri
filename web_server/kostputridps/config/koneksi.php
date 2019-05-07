<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$server = "localhost";
$username = "root";
$password = "";
$database = "kosputridps";
$port = 3306;

$gmap_key = 'GMAP_API_KEY'; // isikan dengan googlemap api key dari google

// Koneksi dan memilih database di server
mysql_connect($server . ':' . $port,$username,$password) or die("Koneksi gagal");
mysql_select_db($database) or die("Database tidak bisa dibuka");
?>
