<?php
// proses akan berjalan ketika ada inputan data_type dan parent_id berupa POST
if(isset($_POST['data_type']) && isset($_POST['parent_id'])){
	$data_type = $_POST['data_type'];
	$parent_id = $_POST['parent_id'];
	/* Custom SQL Query per masing-masing request.
     * setiap field di beri alias id dan nama agar proses di javascript tidak direpotkan
     * oleh penamaan field yang berbeda per tiap jenis data.
     * */
	switch($data_type){
		case 'katsi':
			$sql = "SELECT kode_$data_type id_aliran_gereja, nama_$data_type nama_aliran FROM aliran_gereja WHERE id_kategori_gereja = '$parent_id'"; break;
		default:
			$sql = "SELECT id_aliran_gereja, nama_$data_type nama FROM $data_type";
	}
	mysql_connect('localhost','root','') or die(mysql_error()); // koneksi ke server database
	mysql_select_db('lbsgerejadps')or die(mysql_error()); // pilih database

	$response = array(); // siapkan respon yang nanti akan di convert menjadi JSON
	$query = mysql_query($sql);
	if($query){
		if(mysql_num_rows($query) > 0){
			while($row = mysql_fetch_object($query)){
				// masukan setiap baris data ke variable respon
				$response[] = $row;
			}
		}else{
			$response['error'] = 'Data kosong'; // memberi respon ketika data kosong
		}
	}else{
		$response['error'] = mysql_error(); // memberi respon ketika query salah
	}
	die(json_encode($response)); // convert variable respon menjadi JSON, lalu tampilkan
}
?>
