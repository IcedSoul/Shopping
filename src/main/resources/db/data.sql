
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (1, '酒吞童子', 'SSR！全新的SSR！', '阴阳师;游戏;网易', 199, 200000, 4);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (2, '古剑奇谭二', '国产单机游戏，阿阮我的！', '单机游戏;国产;古剑', 69, 100000, 4);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (3, 'BJD娃娃', '每一个娃娃都是天使，娃妈娃爹就是天使长！', 'BJD;娃娃', 19999, 300000, 4);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (4, '疯狂Android讲义', '这是用来看的嘛？不，这是用来砸人的！装备后攻击+100，防御-100，体力-100，幸运-10，知识+100，固有技能：Android Studio！', '李刚;疯狂系列;Android;安卓', 129, 300000, 3);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (5, '小米MIX', '全面屏！正面都是屏幕！', '小米;全面屏;手机', 3999, 20000, 2);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (6, '雪中飞羽绒服', '少年，穿上这件衣服，下雪你就可以飞了！', '羽绒服;保暖', 799, 10000, 1);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (7, '苹果笔记本', '办公必备', '苹果;笔记本', 19999, 100000, 2);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (8, '化妆品', '比ps好用，你信不', '化妆品', 1999, 10000, 6);
INSERT INTO `products`(`id`, `name`, `description`, `key_word`, `price`, `counts`, `type`) VALUES (9, '足球', '圆圆的，白白的，会滚，一斤三块？要不，来两斤？', '足球;运动', 199, 100000, 7);

INSERT INTO `user_main`(`id`, `name`, `email`, `nick_name`, `role`) VALUES (1, 'admin', 'admin@abc.com', 'admin', 0);
INSERT INTO `user_main`(`id`, `name`, `email`, `nick_name`, `role`) VALUES (2, 'user1', 'user1@abc.com', 'user1', 0);

INSERT INTO `user_detail`(`id`, `password`, `phone_number`, `sex`, `birthday`, `post_number`, `address`, `register_time`) VALUES (1, 'admin', '12345677654', 0, '2000/01/01', '000000', '地球', '2020-01-01 00:00:00');
INSERT INTO `user_detail`(`id`, `password`, `phone_number`, `sex`, `birthday`, `post_number`, `address`, `register_time`) VALUES (2, '123', '11111111111', 0, '2000/01/01', '000000', '地球', '2020-01-01 00:00:00');