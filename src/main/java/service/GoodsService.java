package service;

import bean.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    List<GoodsVo> getGoodsByPage(int pageNo);
    int getPageCount();
    GoodsVo getGoodsById(Integer goodsId);
    Integer saveGoods(GoodsVo newGoods);
    Integer modifyGoods(GoodsVo modifiedGoods);
    Integer deleteGoods(Integer goodsId);
}
