-- cv_design.`user` definition


drop table if exists `cv_resume`;
-- cv_design.cv_resume definition
CREATE TABLE `cv_resume` (
                             `id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                             `file_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简历地址',
                             `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `highest_degree` tinyint unsigned DEFAULT NULL,
                             `tel` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `wechat` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `age` tinyint unsigned DEFAULT NULL,
                             `school` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `intention` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `gender` bit(1) DEFAULT NULL,
                             `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `working_years` tinyint unsigned DEFAULT NULL,
                             `create_at` timestamp NULL DEFAULT NULL,
                             `update_at` timestamp NULL DEFAULT NULL,
                             `upload_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `self_evaluation` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             `political_appearance` tinyint unsigned DEFAULT NULL,
                             `ethnicity` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- cv_design.cv_user definition
drop table if exists `cv_user`;
CREATE TABLE `cv_user` (
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