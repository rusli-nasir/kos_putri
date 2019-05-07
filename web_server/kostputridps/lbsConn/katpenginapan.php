<?php //header("Content-type: application/xml"); ?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
    <channel>
        <title>KATEGORI PENGINAPAN</title>

        <copyright>Copyright 2019</copyright>

        <?php
        include "koneksi.php";
        $query = "SELECT * from katpenginapan ";
        $blog = mysql_query($query);

        while($b=mysql_fetch_array($blog)){

//$url_link = "http://".$b['idc']."";
            ?>
            <item>
                <title><?php echo "$b[nama_kategori]"; ?></title>

                <category><?php echo "$b[id_kategori_penginapan]"; ?></category>


            </item>

        <?php } ?>
    </channel>
</rss>
<?php mysql_close(); ?>

