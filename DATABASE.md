# đơn giản
```
1. Lấy thông tin tất cả các bài viết du lịch mới nhất:
SELECT * FROM Articles ORDER BY created_date DESC;

2. Lấy thông tin chi tiết về một bài viết du lịch cụ thể dựa trên ID:
SELECT * FROM Articles WHERE id = <article_id>;

3. Lấy danh sách các địa điểm du lịch trong một vùng cụ thể:
SELECT * FROM Places WHERE region = <region_name>;

4. Lấy thông tin các địa điểm du lịch liên quan đến một chủ đề cụ thể:
SELECT Places.* FROM Places
JOIN Topics ON Places.id = Topics.place_id
WHERE Topics.name = <topic_name>;

5. Lấy danh sách các bài viết du lịch được người dùng yêu thích nhiều nhất:
SELECT Articles.* FROM Articles
JOIN Likes ON Articles.id = Likes.article_id
GROUP BY Articles.id
ORDER BY COUNT(Likes.user_id) DESC;

6. Lấy thông tin về tất cả các lịch trình du lịch:
SELECT * FROM Itineraries;

7. Lấy danh sách các bài viết du lịch có liên quan đến một địa điểm cụ thể:
SELECT Articles.* FROM Articles
JOIN Places ON Articles.place_id = Places.id
WHERE Places.name = <place_name>;

8. Lấy thông tin về tất cả các phản hồi về một bài viết du lịch:
SELECT * FROM Feedbacks WHERE article_id = <article_id>;

9. Lấy danh sách các tin tức du lịch mới nhất:
SELECT * FROM News ORDER BY created_date DESC;

10. Lấy thông tin về tất cả các người dùng đã đăng ký:
SELECT * FROM Users;

11. Lấy danh sách các bài viết du lịch thuộc một danh mục cụ thể:
SELECT * FROM Articles WHERE topics_id = <topic_id>;

12. Lấy danh sách các bài viết du lịch có phản hồi từ một người dùng cụ thể:
SELECT * FROM Feedbacks WHERE users_id = <user_id>;

13. Lấy danh sách các bài viết du lịch mới nhất theo thời gian tạo:
SELECT * FROM Articles ORDER BY createAt DESC;

14 Lấy danh sách các địa điểm du lịch dựa trên tọa độ kinh độ và vĩ độ:
SELECT * FROM Places WHERE coordinates_id IN (
    SELECT id FROM Coordinates WHERE longitude = <longitude> AND latitude = <latitude>
);

15. Lấy danh sách các địa điểm du lịch trong một địa điểm cha cụ thể:
SELECT * FROM Places WHERE subPlaceId = <parent_place_id>;


```
# Phức tạp
```
1. Lấy danh sách các bài viết du lịch được yêu thích và có số lượng phản hồi lớn hơn một ngưỡng cụ thể:
SELECT Articles.*, COUNT(Feedbacks.id) AS feedback_count
FROM Articles
JOIN Likes ON Articles.id = Likes.articles_id
JOIN Feedbacks ON Articles.id = Feedbacks.articles_id
GROUP BY Articles.id
HAVING COUNT(Likes.id) > <like_threshold> AND COUNT(Feedbacks.id) > <feedback_threshold>;

2. Lấy thông tin về các người dùng đã tạo lịch trình du lịch và số lượng bài viết du lịch mà họ đã viết:
SELECT Users.*, COUNT(Itineraries.id) AS itinerary_count, COUNT(Articles.id) AS article_count
FROM Users
LEFT JOIN Itineraries ON Users.id = Itineraries.users_id
LEFT JOIN Articles ON Users.id = Articles.users_id
GROUP BY Users.id;

3. Lấy danh sách các địa điểm du lịch phổ biến nhất và số lượng bài viết du lịch liên quan đến mỗi địa điểm:
SELECT Places.*, COUNT(Articles.id) AS article_count
FROM Places
LEFT JOIN Articles ON Places.id = Articles.places_id
GROUP BY Places.id
ORDER BY article_count DESC;

4. Lấy danh sách các bài viết du lịch được đánh giá cao nhất dựa trên điểm đánh giá trung bình của phản hồi từ người dùng:
SELECT Articles.*, AVG(Feedbacks.review) AS average_rating
FROM Articles
JOIN Feedbacks ON Articles.id = Feedbacks.articles_id
GROUP BY Articles.id
ORDER BY average_rating DESC;

5. Lấy danh sách các bài viết du lịch gần đây nhất mà người dùng đã thích và có nội dung chứa từ khóa cụ thể:
SELECT Articles.*
FROM Articles
JOIN Likes ON Articles.id = Likes.articles_id
WHERE Likes.users_id = <user_id> AND Articles.content LIKE '%<keyword>%'
ORDER BY Articles.createAt DESC;

6. Lấy danh sách các bài viết du lịch được phản hồi tích cực nhiều nhất trong một khoảng thời gian cụ thể:
SELECT Articles.*, COUNT(Feedbacks.id) AS positive_feedback_count
FROM Articles
JOIN Feedbacks ON Articles.id = Feedbacks.articles_id
WHERE Feedbacks.review = 'positive' AND Feedbacks.createAt BETWEEN '<start_date>' AND '<end_date>'
GROUP BY Articles.id
ORDER BY positive_feedback_count DESC;

7. Lấy danh sách các người dùng có số lượng bài viết du lịch cao nhất và số lượng phản hồi tích cực lớn hơn một ngưỡng cụ thể:
SELECT Users.*, COUNT(Articles.id) AS article_count, COUNT(Feedbacks.id) AS positive_feedback_count
FROM Users
LEFT JOIN Articles ON Users.id = Articles.users_id
LEFT JOIN Feedbacks ON Articles.id = Feedbacks.articles_id AND Feedbacks.review = 'positive'
GROUP BY Users.id
HAVING COUNT(Articles.id) > <article_threshold> AND COUNT(Feedbacks.id) > <positive_feedback_threshold>;

8. Lấy thông tin về các địa điểm du lịch mà người dùng đã thích và có tổng số lượt thích lớn hơn một ngưỡng cụ thể:
SELECT Places.*, COUNT(DISTINCT Likes.users_id) AS like_count
FROM Places
JOIN Articles ON Places.id = Articles.places_id
JOIN Likes ON Articles.id = Likes.articles_id
GROUP BY Places.id
HAVING COUNT(DISTINCT Likes.users_id) > <like_threshold>;

9. Lấy danh sách các bài viết du lịch được đánh giá cao và có số lượng phản hồi lớn hơn một ngưỡng cụ thể:
SELECT Articles.*, AVG(Feedbacks.rating) AS average_rating, COUNT(Feedbacks.id) AS feedback_count
FROM Articles
JOIN Feedbacks ON Articles.id = Feedbacks.articles_id
GROUP BY Articles.id
HAVING AVG(Feedbacks.rating) > <rating_threshold> AND COUNT(Feedbacks.id) > <feedback_threshold>;

10. Lấy danh sách các bài viết du lịch gần đây nhất trong một địa điểm cụ thể và có số lượng phản hồi tích cực lớn hơn một ngưỡng cụ thể:
SELECT Articles.*, COUNT(Feedbacks.id) AS positive_feedback_count
FROM Articles
JOIN Feedbacks ON Articles.id = Feedbacks.articles_id AND Feedbacks.review = 'positive'
WHERE Articles.places_id = <place_id> AND Articles.createAt BETWEEN '<start_date>' AND '<end_date>'
GROUP BY Articles.id
HAVING COUNT(Feedbacks.id) > <positive_feedback_threshold>
ORDER BY Articles.createAt DESC;

```
# Tìm kiếm địa điểm
```
1. Tìm kiếm địa điểm theo tên địa điểm:
SELECT * FROM places WHERE name LIKE '%keyword%';

2. Tìm kiếm địa điểm theo thành phố hoặc Quận:
SELECT *
FROM Places
WHERE name LIKE '%<keyword>%';

3. Tìm kiếm địa điểm dựa trên nội dung:
SELECT * FROM places WHERE content LIKE '%keyword%';

4. Tìm kiếm địa điểm dựa trên từ khóa trong chủ đề:
SELECT p.* FROM places p
JOIN topics t ON p.topics_id = t.id
WHERE t.title LIKE '%keyword%';

5. Tìm kiếm địa điểm dựa trên chủ đề:
SELECT * FROM places WHERE topics_id = 'topic_id';

6. Tìm kiếm địa điểm dựa trên từ khóa trong bài viết liên quan:
SELECT p.* FROM places p
JOIN articles a ON p.id = a.places_id
WHERE a.title LIKE '%keyword%' OR a.content LIKE '%keyword%';

7. Tìm kiếm địa điểm dựa trên từ khóa trong bài viết và chủ đề:
SELECT p.* FROM places p
JOIN articles a ON p.id = a.places_id
JOIN topics t ON a.topics_id = t.id
WHERE (a.title LIKE '%keyword%' OR a.content LIKE '%keyword%') AND t.title LIKE '%topic_keyword%';

8. Tìm kiếm địa điểm dựa trên đánh giá:
SELECT p.* FROM places p
JOIN feedbacks f ON p.id = f.articles_id
WHERE f.heart >= 'min_heart_value' AND f.heart <= 'max_heart_value';


9. Tìm kiếm địa điểm dựa trên người dùng đã thích:
SELECT p.* FROM places p
JOIN likes l ON p.id = l.articles_id
WHERE l.users_id = 'user_id';

10. Tìm kiếm địa điểm dựa trên từ khóa trong các bài viết liên quan và sắp xếp theo đánh giá trung bình:
SELECT p.*, AVG(f.rating) AS average_rating
FROM places p
JOIN articles a ON p.id = a.places_id
JOIN feedbacks f ON a.id = f.articles_id
WHERE a.title LIKE '%keyword%' OR a.content LIKE '%keyword%'
GROUP BY p.id
ORDER BY average_rating DESC;


```
# Bộ lọc
```
1. Lọc địa điểm theo đánh giá:
SELECT a.*
FROM articles a
ORDER BY (
    SELECT COUNT(*) 
    FROM likes l
    WHERE l.articles_id = a.id
) DESC;



2. Lọc địa điểm theo thể loại:
SELECT a.*
FROM articles a
JOIN topics t ON a.topics_id = t.id
WHERE t.title = 'Tên chủ đề';

3. Lọc địa điểm theo địa điểm gần đây:


4. Lọc địa điểm theo thời gian hoạt động:


5. Lọc địa điểm theo đặc tính địa điểm: (ví dụ: 'núi', 'biển', 'di tích lịch sử', 
                                                    Thiên nhiên và thế giới bên ngoài
                                                    Truyền thống
                                                    Đồ ăn & Thức uống
                                                    Nghệ thuật & Văn hóa
                                                    Các thành phố
                                                    Thư giãn
                                                    Đặc biệt).
                                                    SELECT p.*
FROM places p
JOIN topics_place tp ON p.id = tp.place_id
JOIN topics t ON tp.topic_id = t.id
WHERE t.title = 'Thiên nhiên và thế giới bên ngoài';



6. Lọc địa điểm theo sắp xếp:(ví dụ: 'giá', 'đánh giá', 'tên')
SELECT *
FROM places
ORDER BY column_name ASC/DESC;


```
# Lập kế hoạch
```
1. Lấy danh sách các bài viết đã được người dùng lưu vào kế hoạch của mình:
SELECT * FROM articles WHERE id IN (SELECT articles_id FROM itineraries WHERE users_id = <id_người_dùng>);



```

# PROJECT

Màn hình 1:

- 

- 
