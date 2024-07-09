<?php
    include("connect.php");

    // $tenKhachHang = "namm";
    // $soDienThoai = "0123455";
    // $email = "nam@gmail.com";

    $tenKhachHang = $_POST["tenkhachhang"];
    $soDienThoai = $_POST["sodienthoai"];
    $email = $_POST["email"];

    if(strlen($tenKhachHang) > 0 && strlen($soDienThoai) > 0 && strlen($email) > 0 ) {
        $query = "INSERT INTO donhang(id, tenKhachHang, soDienThoai, email)
                    VALUES (NULL, '$tenKhachHang', '$soDienThoai', '$email')";

        if(mysqli_query($conn, $query)) {
            $id_donHang = $conn->insert_id;
            echo $id_donHang;
        }    
        else{
            echo "Thất bại";
        }
    }
    else{
        echo "Kiểm tra lại dữ liệu...";
    }
?>