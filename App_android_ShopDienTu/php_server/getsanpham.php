<?php

include("connect.php");

$page = $_GET['page'];
$idsp = $_POST['idsanpham'];  
//$idsp = $_POST['id_loaisp'];  

$space = 5;  // giới hạn GET dl về , thop này là 5

$limit = ($page - 1) * $space; // xacs định vị trí đầu tiên load

$arr_sanpham = array();

$query = "SELECT * FROM sanpham WHERE idsanpham = $idsp LIMIT $limit,$space";
$data = mysqli_query($conn, $query);

while($row = mysqli_fetch_assoc($data)) {
    array_push($arr_sanpham, new Sanpham(
                                $row["id"],
                                $row["tensanpham"],
                                $row["giasanpham"],
                                $row["hinhanhsanpham"],
                                $row["motasanpham"],
                                $row["idsanpham"]
                            )
                );
}

echo json_encode($arr_sanpham);

class Sanpham{
    public $id;
    public $tensp;
    public $giasanpham;
    public $hinhanhsanpham;
    public $motasp;
    public $idsanpham;
    function __construct($id, $tensp, $giasanpham, $hinhanhsanpham, $motasp, $idsanpham){
        $this->id = $id;
        $this->tensp = $tensp;
        $this->giasanpham = $giasanpham;
        $this->hinhanhsanpham = $hinhanhsanpham;
        $this->motasp = $motasp;
        $this->idsanpham = $idsanpham;
    }
}

?>