package dao;

import bean.vo.GoodsVo;

import java.util.List;


//创建对商品操作的接口
public interface GoodsDAO {
    List<GoodsVo> getGoodsByPage(int pageNo);
    GoodsVo getGoodsById(Integer goodsId);
    int getPageCount();
    Integer saveGoods(GoodsVo newGoods);
    Integer modifyGoods(GoodsVo modifiedGoods);
    Integer deleteGoods(Integer goodsId);
}
