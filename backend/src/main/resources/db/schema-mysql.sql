-- cv_design.cv_job definition

CREATE TABLE `cv_job`
(
    `id`             varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `company_name`   varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `head_count`     int                                                           DEFAULT NULL,
    `position_name`  varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `info`           varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `max_salary`     int                                                           DEFAULT NULL,
    `min_salary`     int                                                           DEFAULT NULL,
    `remark`         varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `create_at`      timestamp NULL DEFAULT NULL,
    `update_at`      timestamp NULL DEFAULT NULL,
    `user_name`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `min_degree`     tinyint                                                       DEFAULT NULL,
    `min_work_years` tinyint                                                       DEFAULT NULL,
    UNIQUE KEY `cv_job_id_IDX` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- cv_design.cv_resume definition

CREATE TABLE `cv_resume`
(
    `id`                   varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `file_url`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简历地址',
    `file_name`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `name`                 varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `highest_degree`       tinyint unsigned DEFAULT NULL,
    `tel`                  varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `wechat`               varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `email`                varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `age`                  tinyint unsigned DEFAULT NULL,
    `school`               varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `intention`            varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `gender`               bit(1)                                                        DEFAULT NULL,
    `avatar_url`           varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `working_years`        tinyint unsigned DEFAULT NULL,
    `create_at`            timestamp NULL DEFAULT NULL,
    `update_at`            timestamp NULL DEFAULT NULL,
    `upload_user`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL,
    `self_evaluation`      varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `political_appearance` tinyint unsigned DEFAULT NULL,
    `ethnicity`            varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- cv_design.cv_resume_tag definition

CREATE TABLE `cv_resume_tag`
(
    `resume_id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `tag_id`    varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    UNIQUE KEY `cv_resume_tag_resume_id_IDX` (`resume_id`,`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- cv_design.cv_tag definition

CREATE TABLE `cv_tag`
(
    `id`   varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
    `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `cv_tag_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- cv_design.cv_user definition

CREATE TABLE `cv_user`
(
    `username`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `phone`     varchar(11) COLLATE utf8mb4_general_ci                        DEFAULT NULL,
    `salt`      varchar(100) COLLATE utf8mb4_general_ci                       DEFAULT NULL,
    `password`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `avatar`    varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
    `gender`    bit(1)                                                        DEFAULT NULL,
    `nickname`  varchar(15) COLLATE utf8mb4_general_ci                        DEFAULT NULL,
    `create_at` timestamp NULL DEFAULT NULL,
    `update_at` timestamp NULL DEFAULT NULL,
    `role`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '身份',
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;