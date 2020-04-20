EXPLAIN SELECT seckill.id as id, seckill.goods_id, seckill.goods_name,
               seckill_stock, seckill_starttime, seckill_endtime, seckill_creationtime,
               seckill_price, goods_title, goods_img, goods_detail, goods_price, goods_stock
        FROM seckill LEFT JOIN goods on (goods.id = seckill.goods_id)
        where seckill.id = 1008;

explain SELECT seckill.id as id, seckill.goods_id, seckill.goods_name, seckill_stock,seckill_starttime,
               seckill_endtime, seckill_creationtime,seckill_price, goods_title, goods_img, goods_detail,
               goods_price, goods_stock
        FROM
            seckill LEFT JOIN goods on (goods.id = seckill.goods_id)
        WHERE
                seckill_endtime > '2020-04-18 00:00:000'
        AND
                seckill_endtime <= '2020-04-19 00:00:000';

explain SELECT
            seckill_id, user_phone, seckilled_state, seckilled_creationtime, seckilled_price, seckilled_number, seckilled.goods_id as goods_id,
            seckill.goods_name as goods_name, seckill_stock, seckill_starttime, seckill_endtime, seckill_creationtime,
            goods_title, goods_img, goods_detail, goods_price, goods_stock
        FROM seckilled LEFT JOIN seckill on(seckill.id = seckilled.seckill_id) LEFT JOIN goods on(goods.id = seckilled.goods_id) WHERE seckill_id in (1008) and user_phone = 15398923162
-- MRR 将二级索引找到的主键排序，然后再回表，减少磁盘的随机访问
set @@optimizer_switch='mrr=on,mrr_cost_based=off'
