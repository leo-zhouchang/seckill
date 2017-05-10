/*创建数据库*/
CREATE DATABASE seckill;

USE seckill;

/* 创建秒杀活动表结构*/
CREATE TABLE `seckill`(
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  name VARCHAR(200) NOT NULL COMMENT '秒杀商品名称',
  number INT NOT NULL COMMENT '商品库存数量',
  start_time DATETIME NOT NULL COMMENT '秒杀开始时间',
  end_time DATETIME NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '秒杀活动创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT '秒杀活动表';

/*初始化秒杀活动表数据*/
INSERT INTO `seckill`(name, number, start_time, end_time)
VALUES
  ('特价1000元秒杀Iphone6',10,'2016-12-03 00:00:00','2016-12-04 00:00:00'),
  ('特价500元秒杀Ipad2',20,'2016-12-12 00:00:00','2016-12-12 12:12:12'),
  ('特价400元秒杀小米4',30,'2016-12-04 00:00:00','2016-12-05 00:00:00'),
  ('特价200元秒杀三星Note7',40,'2016-12-05 00:00:00','2016-12-06 00:00:00');


/*创建订单明细表结构*/
CREATE TABLE seckill.`order_detail`(
  user_id VARCHAR(32) NOT NULL COMMENT '抢购成功的用户的ID',
  seckill_id BIGINT NOT NULL COMMENT '秒杀商品的ID',
  status TINYINT NOT NULL DEFAULT -1 COMMENT '订单状态 -1：无效，1：待付款，2：已付款，3：已发货',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '订单创建时间',
  PRIMARY KEY (user_id,seckill_id),/*联合主键 可以防止用户对同一个商品多次秒杀*/
  KEY idx_create_time(create_time)

)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '订单明细表';
