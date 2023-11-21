INSERT INTO category (title) VALUES
  ('밥'),
  ('면'),
  ('국'),
  ('고기');

INSERT INTO item (category_id, name, price, stock, description, created_date) VALUES
(1, '밥상품1', 10000, 50, '맛있는 밥 상품입니다.', NOW()),
(2, '면상품1', 8000, 30, '신라면 신제품입니다.', '2023-11-10'),
(3, '국상품1', 12000, 20, '고기국입니다.', '2023-11-12'),
(4, '고기상품1', 20000, 10, '신선한 소고기 상품입니다.', '2023-11-13'),
(1, '밥상품2', 10000, 50, '맛있는 밥 상품입니다.', '2023-10-10'),
(2, '면상품2', 8000, 30, '신라면 신제품입니다.', '2023-10-11'),
(3, '국상품2', 12000, 20, '고기국입니다.', '2023-10-12');

INSERT INTO USERS (id, password, nickname, phone_number, role_types, created_date) VALUES
('honey123', '$2a$10$GNJzkOmrcixwpb8bHLvwR.UXDfm9jZMkywEo.FxdaMRKTomrf27BK', 'userNickname', '010-1234-5678', 'USER', NOW()),
('admin123', '$2a$10$GNJzkOmrcixwpb8bHLvwR.UXDfm9jZMkywEo.FxdaMRKTomrf27BK', 'adminNickname', '010-1234-5678', 'USER,ADMIN', NOW());