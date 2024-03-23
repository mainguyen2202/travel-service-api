SELECT * FROM db_travels.users;
INSERT INTO `db_travels`.`users` (`id`, `create_at`, `email`, `firstname`, `image`, `lastname`, `password`, `role`, `status`, `username`) VALUES ('1', '2024-03-20', 'trucmainguyen02@gmail.com', 'Mai', 'avartar.jpg', 'Nguyen', 'abc123', '1', '1', 'mainguyen');





INSERT INTO `users` (`id`, `create_at`, `email`, `firstname`, `image`, `lastname`, `password`, `role`, `status`, `username`)
VALUES
  (1, '2022-01-01', CONCAT('mai', '@gmail.com'), 'Mai', 'image1.jpg', 'Đỗ', 'password1', 1, 1, 'user1'),
  (2, '2022-02-01', CONCAT('my', '@gmail.com'), 'Mỹ', 'image2.jpg', 'Nguyễn', 'password2', 2, 1, 'user2'),
  (3, '2022-03-01', CONCAT('linh', '@gmail.com'), 'Linh', 'image3.jpg', 'Trần', 'password3', 2, 0, 'user3'),
  (4, '2022-04-01', CONCAT('duy', '@gmail.com'), 'Duy', 'image4.jpg', 'Lê', 'password4', 1, 1, 'user4'),
  (5, '2022-05-01', CONCAT('huong', '@gmail.com'), 'Hương', 'image5.jpg', 'Vũ', 'password5', 2, 1, 'user5'),
  (6, '2022-06-01', CONCAT('an', '@gmail.com'), 'An', 'image6.jpg', 'Phạm', 'password6', 1, 0, 'user6'),
  (7, '2022-07-01', CONCAT('thanh', '@gmail.com'), 'Thành', 'image7.jpg', 'Hoàng', 'password7', 2, 1, 'user7'),
  (8, '2022-08-01', CONCAT('thu', '@gmail.com'), 'Thu', 'image8.jpg', 'Huỳnh', 'password8', 1, 1, 'user8'),
  (9, '2022-09-01', CONCAT('hai', '@gmail.com'), 'Hải', 'image9.jpg', 'Ngô', 'password9', 2, 1, 'user9'),
  (10, '2022-10-01', CONCAT('thao', '@gmail.com'), 'Thảo', 'image10.jpg', 'Bùi', 'password10', 1, 0, 'user10');

  INSERT INTO `topics` (`id`, `title`, `sub_topics_id`, `content`, `status`) VALUES
(1, 'Thiên nhiên', NULL, NULL, 1),
(2, 'Truyền thống', NULL, NULL, 1),
(3, 'Nghệ thuật & văn hóa', NULL, NULL, 1),
(4, 'Đồ ăn & thức uống', NULL, NULL, 1),
(5, 'Thư giãn', NULL, NULL, 1),
(6, 'Mua sắm', NULL, NULL, 1),
(7, 'Khu vui chơi', NULL, NULL, 1),
(8, 'Biển', 1, NULL, 1),
(9, 'Núi', 1, NULL, 1),
(10, 'Đảo', 1, NULL, 1),
(11, 'Suối', 1, NULL, 1),
(12, 'Hồ', 1, NULL, 1),
(13, 'Hang động', 1, NULL, 1),
(14, 'Đồng cỏ', 1, NULL, 1),
(15, 'Rừng nhiệt đới', 1, NULL, 1),
(16, 'Công viên', 1, NULL, 1),
(17, 'Làng cổ', 2, NULL, 1),
(18, 'Làng nghề truyền thống', 2, NULL, 1),
(19, 'Di tích lịch sử', 2, NULL, 1),
(20, 'Lễ hội truyền thống', 2, NULL, 1),
(21, 'Nghệ thuật dân gian', 2, NULL, 1),
(22, 'Nghệ thuật địa phương', 2, NULL, 1),
(23, 'Nhà hát', 3, NULL, 1),
(24, 'Bảo tàng', 3, NULL, 1),
(25, 'Thủ công & mỹ nghệ', 3, NULL, 1),
(26, 'Nghệ thuật biểu diễn', 3, NULL, 1),
(27, 'Triển lãm nghệ thuật', 3, NULL, 1),
(28, 'Di tích văn hóa', 3, NULL, 1),
(29, 'Lễ hội truyền thống', 3, NULL, 1),
(30, 'Nghệ nhân địa phương', 3, NULL, 1),
(31, 'Nghệ thuật ăn uống', 4, NULL, 1),
(32, 'Ẩm thực địa phương', 4, NULL, 1),
(33, 'Ẩm thực đường phố', 4, NULL, 1),
(34, 'Món ăn đặc sản', 4, NULL, 1),
(35, 'Nhà hàng truyền thống', 4, NULL, 1),
(36, 'Khu nghỉ dưỡng', 5, NULL, 1),
(37, 'Spa và xông hơi', 5, NULL, 1),
(38, 'Yoga và thiền', 5, NULL, 1),
(39, 'Khu vườn hoa', 5, NULL, 1),
(40, 'Chợ truyền thống', 6, NULL, 1),
(41, 'Trung tâm thương mại', 6, NULL, 1),
(42, 'Cửa hàng đặc sản', 6, NULL, 1),
(43, 'Công viên giải trí', 7, NULL, 1),
(44, 'Khu vui chơi gia đình', 7, NULL, 1),
(45, 'Công viên nước', 7, NULL, 1),
(46, 'Khu trò chơi trong nhà', 7, NULL, 1),
(47, 'Khu vui chơi ngoài trời', 7, NULL, 1);