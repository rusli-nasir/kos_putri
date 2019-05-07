<?php
session_start();
if (empty($_SESSION['namauser']) AND empty($_SESSION['passuser'])){
    echo "<link href='../config/adminstyle.css' rel='stylesheet' type='text/css'>
 <center>Untuk mengakses modul, Anda harus login <br>";
    echo "<a href=index.php><b>LOGIN</b></a></center>";
}
else{
    ?>

    <html>
    <head>
        <title>:- KOST PUTRI DENPASAR -:</title>
        <link href="../config/adminstyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body>


    <div id="header">
        <div id="content">
            <?php include "content.php"; ?>
        </div>

        <div id="menu">
            <ul>
                <li><a href=?module=home>&#187; Beranda</a></li>
                <?php include "menu.php"; ?>
                <li><a href=logout.php>&#187; Keluar</a></li>
            </ul>
            <p>&nbsp;</p>
        </div>

        <div id="footer">
            =====Copyright &copy; 2019=====
        </div>
    </div>

    </body>
    </html>
    <?php
}
?>
