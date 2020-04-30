package service;

import bean.vo.GoodsVo;
import bean.vo.Item;

import java.util.ArrayList;

//设置对购物车操作的服务接口
public interface CartService {
    public void addToCart(Integer goodsId, int quantity);
    public void update(Integer goodsId, int quantity);
    public void delete(Integer goodsId);
    public ArrayList<Item> getCart();
    public void setCart(ArrayList<Item> cart);

}

