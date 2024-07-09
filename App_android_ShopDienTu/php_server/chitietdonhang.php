<?php
    include "connect.php";

   // $json = '[{"giasanpham":6990000, "madonhang": "5", "soluongsanpham" : 1, "tensanpham":"Laptop Lenovo Ideapad110","masanpham":20},{"giasanpham":29970000, "madonhang":"5","soluongsanpham": 3,"tensanpham":"Dien thoai sony z5","masanpham": 21}]';

   $json = $_POST['json'];
    $data = json_decode($json, true);

    foreach($data as $value){
        $madonhang = $value['madonhang'];
        $masanpham = $value['masanpham'];
        $tensanpham = $value['tensanpham'];
        $giasanpham = $value['giasanpham'];
        $soluongsanpham = $value['soluongsanpham'];

        $query = "Insert into chitietdonhang (id, madonhang, masanpham, tensanpham, giasanpham, soluongsanpham)
            VALUES (null, '$madonhang', '$masanpham','$tensanpham', '$giasanpham', '$soluongsanpham')";

        $Data_query = mysqli_query($conn, $query);
    }

    if($Data_query){
        echo "1";
    }
    else{
        echo "0";
    }
?>