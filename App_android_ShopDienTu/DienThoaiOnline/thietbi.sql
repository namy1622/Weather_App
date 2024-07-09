-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 09, 2024 lúc 11:04 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thietbi`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(10000) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, 5, 20, 'Laptop Lenovo Ideapad110', 6990000, 1),
(2, 5, 21, 'Dien thoai sony z5', 29970000, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenKhachHang` varchar(200) NOT NULL,
  `soDienThoai` int(11) NOT NULL,
  `email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenKhachHang`, `soDienThoai`, `email`) VALUES
(1, 'namm', 123455, 'nam@gmail.com'),
(2, 'namm', 123455, 'nam@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Điện thoại ', 'https://cdn-icons-png.flaticon.com/128/9513/9513547.png'),
(2, 'Laptop ', 'https://cdn-icons-png.flaticon.com/128/2704/2704414.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` varchar(10000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Xiaomi note 12Pro 5G', 4500000, 'https://th.bing.com/th/id/OIP.XIyRZxB3PKb-x_hq8wEtOgAAAA?rs=1&pid=ImgDetMain', 'Sản phẩm thuộc hãng Xiaomi, xuất xứ Trung Quốc,...', 1),
(2, 'Iphone 12 ProMax 256GB', 1250000, 'https://th.bing.com/th/id/R.f492814c56e9a95f56749c11d8d2837f?rik=FMJYxX%2bNLQYgcA&pid=ImgRaw&r=0', 'Điện thoại thế hệ thứ 12 của Apple, thuộc dòng ProMax', 1),
(3, 'IphoneXs', 4600000, 'https://th.bing.com/th?id=OIP.g_pFHMNy39W1mU9_j6D9HAHaHa&w=298&h=298&c=10&rs=1&qlt=99&bgcl=fffffe&r=0&o=6&dpr=1.3&pid=13.1', 'iPhone Xs là cụm từ được rất nhiều người mong chờ muốn biết và tìm kiếm trên Google bởi đây là chiếc điện thoại mà Apple kỉ niệm 10 năm iPhone đầu tiên được bán ra.', 1),
(4, 'Samsung S24 ultra', 3199999, 'https://th.bing.com/th/id/OIP.evJadDOV8fCafwFpfXlCFwHaHb?w=169&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', 'Samsung S24 Ultra là siêu phẩm smartphone đỉnh cao mở đầu năm 2024 đến từ nhà Samsung với chip Snapdragon 8 Gen 3 For Galaxy mạnh mẽ, công nghệ tương lai Galaxy AI cùng khung viền Titan đẳng cấp hứa hẹn sẽ mang tới nhiều sự thay đổi lớn về mặt thiết kế và cấu hình. SS Galaxy S24 bản Ultra sở hữu màn hình 6.8 inch Dynamic AMOLED 2X tần số quét 120Hz. Máy cũng sở hữu camera chính 200MP, camera zoom quang học 50MP, camera tele 10MP và camera góc siêu rộng 12MP.', 1),
(5, 'Lenovo Thinkpad T470 Core i5-7200U RAM 8GB SSD 256GB 14 inch FHD Windows 10 Pro', 11400000, 'https://www.laptopvip.vn/images/ab__webp/thumbnails/1100/900/detailed/17/lenovo-laptop-thinkpad-t470-hero.png.webp', 'Thương hiệu :\r\nLenovo\r\nBộ nhớ RAM\r\nRAM :\r\n8 GB\r\nLoại RAM :\r\nDDR4\r\nTốc độ BUS :\r\n2133 MHz\r\nỔ cứng\r\nDung lượng ổ cứng :\r\n256GB\r\nMàn hình\r\nKích thước màn hình:\r\n14 inch\r\nCard đồ họa\r\nBộ nhớ đồ họa :\r\nShare\r\nGiao tiếp\r\nBluetooth :\r\nBluetooth 4.1\r\nUSB :\r\n3x USB 3.0\r\nPIN/Battery\r\nThông tin Pin :\r\n6 cell - 48 WHr\r\nKeyboard & Touchpad\r\nBàn phím số :\r\nKhông\r\nThông tin khác\r\nMàu sắc :\r\nBlack\r\nKích thước - Trọng lượng\r\nKích thước :\r\n336.6 mm x 232.5 mm x 19.95 mm, 13.25\" x 9.15\" x 0.79\"\r\nBảo hành - Xuất xứ\r\nBảo hành :\r\n6 Tháng\r\nTiêu chuẩn hàng hóa :\r\nLike New 99% - Full Box\r\nXuất xứ :\r\nNhập khẩu từ USA', 2),
(6, 'Apple MacBook Air M1 256GB 2020', 2499999, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:0/q:90/plain/https://cellphones.com.vn/media/wysiwyg/laptop/macbook/Air/M1-2020/macbook-air-2020-m1-3.jpg', 'Macbook Air M1 2020 - Sang trọng, tinh tế, hiệu năng khủng\r\nMacbook Air M1 là dòng sản phẩm có thiết kế mỏng nhẹ, sang trọng và tinh tế cùng với đó là giá thành phải chăng nên MacBook Air đã thu hút được đông đảo người dùng yêu thích và lựa chọn. Đây cũng là một trong những phiên bản Macbook Air mới nhất mà khách hàng không thể bỏ qua khi đến với CellphoneS. Dưới đây là chi tiết về thiết kế, cấu hình của máy.', 2),
(7, 'Điện thoại iPhone 6s 16GB', 4500000, 'https://cdn.tgdd.vn/Products/Images/42/71306/iphone-6s-vang-hong-org-4.jpg', 'iPhone 6s xứng đáng là phiên bản nâng cấp hoàn hảo từ chiếc điện thoại thông minh iPhone 6 với nhiều tính năng mới hấp dẫn.', 1),
(8, 'Tecno Pova 4 Pro 8GB 128GB', 4990000, 'https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/j/o/jowi_t.jpg', 'Hiệu năng vượt trội với chip MediaTek Helio G99 kết hợp cùng RAM 8 GB giúp đa nhiệm tốt\r\nMàn hình AMOLED cùng tốc độ làm mới 90 Hz cho chất lượng hiển thị đẹp mắt và mượt mà\r\nNâng cấp trải nghiệm nghe với hệ thống loa kép và công nghệ âm thanh DTS cực chất lượng\r\nViên pin khủng 6000 mAh kết hợp sạc nhanh 45 W giúp tiết kiệm tối đa thời gian sạc đầy pin', 1),
(9, 'Samsung Galaxy A23 5G', 4150000, 'https://cdn2.cellphones.com.vn/insecure/plain/https://cellphones.com.vn/media/catalog/product//1/5/15_20.jpg', 'Galaxy A23 5G là một trong những mẫu smartphone được yêu thích nhất của Samsung ở phân khúc tầm trung hiện nay nhờ viên pin lớn 5,000 mAh và hiệu năng khỏe, ổn định cho khả năng chiến game mượt mà.', 1),
(10, 'Laptop MSI Modern 15 B12MO i5 1235U/8GB/512GB/Win11', 11990000, 'https://cdn.tgdd.vn/Products/Images/44/310449/Slider/vi-vn-msi-modern-15-b12mo-i5-625vn-sliderr-6.jpg', 'Laptop MSI Modern 15 B12MO i5 1235U (625VN) mang đam mê của bạn bắt nhịp với lối sống năng động hiện đại. Dù là bạn đang bận rộn trong văn phòng hay làm việc trên giảng đường, vi xử lý Intel Core Alder lake thế hệ mới cũng sẽ đáp ứng mọi nhu cầu của bạn.', 2),
(11, 'Điện thoại iPhone 15 Pro Max 256GB', 297900000, 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-thumbnew-600x600.jpg', 'Thông số kỹ thuật:\r\nMàn hình: 6.7 inch, OLED, Super Retina XDR (1290 x 2796 Pixels), 120 Hz\r\nChip: Apple A17 Pro 6 nhân\r\nRAM: 8 GB\r\nROM: 256 GB\r\nCamera: Camera sau: Chính 48 MP & Phụ 12 MP, 12 MP. Camera trước: 12 MP\r\nPin, sạc: 4422 mAh, 20 W', 1),
(12, 'Điện thoại iPhone 14 Pro Max 128GB', 26990000, 'https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg', 'Phone 14 Pro Max có thiết kế màn hình đục lỗ độc đáo, thay thế notch truyền thống, mang lại diện mạo mới mẻ, hiện đại và tối ưu không gian hiển thị. Màn hình 6.7 inch độ phân giải Super Retina XDR, mang đến trải nghiệm xem phim, chơi game, lướt web,... sắc nét, sống động và chân thực.', 1),
(13, 'Điện thoại Samsung Galaxy S23 Ultra 5G 8GB/256GB', 24990000, 'https://cdn.tgdd.vn/Products/Images/42/249948/samsung-galaxy-s23-ultra-green-thumbnew-600x600.jpg', 'Thông số kỹ thuật:\r\nMàn hình: 6.8 inch, Dynamic AMOLED 2X, 2K+, 120 Hz\r\nChip: MediaTek Dimensity 7050 5G 8 nhân\r\nRAM: 8 GB\r\nROM: 256 GB\r\nCamera: Camera sau: Chính 200 MP & Phụ 12 MP, 10 MP, 10 MP. Camera trước: 12 MP\r\nPin, sạc: 5000 mAh, 45 W', 1),
(14, 'Điện thoại Samsung Galaxy A54 5G 8GB/128GB', 7799000, 'https://cdn.tgdd.vn/Products/Images/42/303585/samsung-galaxy-a54-5g-tim-thumb-1-600x600.jpg', 'Thông số kỹ thuật:\r\nMàn hình: 6.4 inch, Super AMOLED, 120 Hz\r\nChip: Exynos 1380 8 nhân\r\nRAM: 48GB\r\nROM: 128 GB\r\nCamera: Camera sau: Chính 50 MP & Phụ 12 MP, 5 MP. Camera trước: 32 MP\r\nPin, sạc: 5000 mAh, 25 W', 1),
(15, 'OPPO A98 5G 8GB/256GB', 6990000, 'https://cdn.tgdd.vn/Products/Images/42/307891/oppo-a98-5g-xanh-thumb-1-2-600x600.jpg', 'Thông số kỹ thuật:\r\nMàn hình: 6.72 inch, LTPS LCD, Full HD+, 120 Hz\r\nChip: Snapdragon 695 5G 8 nhân\r\nRAM: 8 GB\r\nROM: 256 GB\r\nCamera: Camera sau: Chính 64 MP & Phụ 32 MP, 8 MP. Camera trước: 32 MP\r\nPin, sạc: 5000 mAh, 67 W', 1),
(16, 'Điện thoại OPPO A17K 3GB/64GB', 2790000, 'https://cdn.tgdd.vn/Products/Images/42/288404/oppo-a17k-vang-thumb-%C4%83-600x600.jpg', 'Thông số kỹ thuật:\r\nMàn hình: 6.56 inch, IPS LCD, HD+, 60 Hz\r\nChip: MediaTek Helio G35 8 nhân\r\nRAM: 3 GB\r\nROM: 64 GB\r\nCamera: Camera sau: Chính 8 MP. Camera trước: 5 MP\r\nPin, sạc: 5000 mAh, 10 W', 1),
(17, 'Điện thoại Xiaomi Redmi 12 4GB/128GB', 0, 'https://cdn.tgdd.vn/Products/Images/42/307556/xiaomi-redmi-12-den-thumb-text-600x600.jpg', 'Điện thoại Xiaomi Redmi này sở hữu thiết kế tối giản, hiện đại với mặt lưng kính bóng bẩy, mang đến vẻ ngoài thời trang và trẻ trung. Thiết kế mỏng nhẹ giúp người dùng cầm nắm thoải mái, sử dụng lâu dài mà không gặp khó khăn. Mặt trước của máy sử dụng kiểu nốt ruồi giúp mở rộng diện tích hiển thị, tối ưu viền bezel, mang đến trải nghiệm giải trí trọn vẹn và thú vị hơn.\r\nThông số kỹ thuật:\r\nMàn hình: IPS LCD, 6.79\", Full HD+\r\nChip: MediaTek Helio G88\r\nRAM: 4 GB\r\nROM: 128 GB\r\nCamera: Chính 50 MP & Phụ 8 MP, 2 MP\r\nCamera sau: 8 MP\r\nPin, sạc: 5000 mAh, 18 W\r\n', 1),
(18, 'Điện thoại realme C53 6GB/128GB', 0, 'https://cdn.tgdd.vn/Products/Images/42/306785/realme-c53-gold-thumb-1-600x600.jpg', 'Thông số kỹ thuật:\r\nMàn hình: 6.74 inch, IPS LCD, HD+, 120 Hz\r\nChip: Unisoc Tiger T612\r\nRAM: 6 GB\r\nROM: 128 GB\r\nCamera: Camera sau: Chính 50 MP & Phụ 0.08 MP. Camera trước: 8 MP\r\nPin, sạc: 5000 mAh, 33 W', 1),
(19, 'Điện thoại vivo Y22s 8GB', 3390000, 'https://cdn.tgdd.vn/Products/Images/42/285224/vivo-y22s-vang-thumb-600x600.jpg', 'Thông số kỹ thuật:\r\nMàn hình: IPS LCD, 6.55\", HD+\r\nChip: Snapdragon 680\r\nRAM: 8 GB\r\nROM: 128 GB\r\nCamera: Chính 50 MP & Phụ 2 MP\r\nCamera trước: 8 MP\r\nPin, sạc: 5000 mAh, 18 W\r\nVivo Y22s sở hữu thiết kế vuông vức hiện đại với các cạnh và mặt lưng vát phẳng tinh tế. Thân máy thanh mảnh cùng mặt lưng nhựa Polymer cao cấp tạo cảm giác cầm nắm thoải mái. Máy có hai phiên bản màu xanh và xanh đen, được phủ lớp nhám nhẹ giúp tăng độ bám tay và tạo hiệu ứng lấp lánh sang trọng.', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
