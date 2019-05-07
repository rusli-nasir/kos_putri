<?php //header("Content-type: application/xml"); ?>
    <rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
        <channel>
            <title>ALIRAN GEREJA</title>


            <copyright>Copyright 2016</copyright>

            <?php
            include "koneksi.php";
            $query = "SELECT * from aliran_gereja where id_kategori_gereja = '".$_GET['idkatgereja']."' ";
            $blog = mysql_query($query);

            while($b=mysql_fetch_array($blog)){

//$url_link = "http://".$b['idc']."";
                ?>
                <item>
                    <title><?php echo "$b[nama_aliran]"; ?></title>
                    <link><?php echo "$b[id_kategori_gereja]"; ?></link>
                    <category><?php echo "$b[id_aliran_gereja]"; ?></category>


                </item>

            <?php } ?>
        </channel>
    </rss>
<?php mysql_close(); ?>