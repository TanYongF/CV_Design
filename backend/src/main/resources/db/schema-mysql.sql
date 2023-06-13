-- cv_design.`user` definition


drop table if exists `user`;
CREATE TABLE `user` (
                        `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                        `phone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `salt` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                        `avatar` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `gender` bit(1) DEFAULT NULL,
                        `nickname` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `create_at` timestamp NULL DEFAULT NULL,
                        `update_at` timestamp NULL DEFAULT NULL,
                        `role` tinyint(1) NOT NULL DEFAULT '0' COMMENT '身份',
                        PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;