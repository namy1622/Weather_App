<?php
     include "connect.php";
          // Đặt tiêu đề HTTP là JSON
          header('Content-Type: application/json');
//------------------------------------------------
    class Loaisp{
       
        public $id;
        public $tenloaisp;
        public $hinhanhloaisp;

        function __construct($id, $tenloaisp, $hinhanhloaisp){
            $this->id = $id;
            $this->tenloaisp = $tenloaisp;
            $this->hinhanhloaisp = $hinhanhloaisp;
        }
    }
//------------------------------------------------
    $query = "SELECT * FROM loaisanpham";
     $data = mysqli_query($conn, $query);

     $arr_loaisp = array();
     while($row = mysqli_fetch_assoc($data)){ // đi vào từng dòng trong data
        array_push($arr_loaisp, new Loaisp(
            $row['id'],
            $row['tenloaisanpham'],
            $row['hinhanhloaisanpham']
        ));
     }
     echo json_encode($arr_loaisp);
?>