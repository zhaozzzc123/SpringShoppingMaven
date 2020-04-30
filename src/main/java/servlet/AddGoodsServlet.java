package servlet;

import bean.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.GoodsService;
import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebService(name = "/addGoods")
public class AddGoodsServlet  extends HttpServlet {

   @Autowired
   GoodsService goodsService;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goodsId = request.getParameter("goodsId");
        String goodsName = request.getParameter("goodsName");
        String price = request.getParameter("price");

        GoodsVo newGoods = new GoodsVo();
        newGoods.setGoodsId(Integer.parseInt(goodsId));
        newGoods.setGoodsName(goodsName);
        newGoods.setPrice(Float.parseFloat(price));

        Integer rst = goodsService.saveGoods(newGoods);

        String rstPage = "getAllGoods";

        if(rst != 1){
            String errorMessage = "添加商品出错！";
            request.setAttribute("errorMessage",errorMessage);
        }else{
            String infoMessage = "添加商品成功！";
            request.setAttribute("errorMessage",infoMessage);
        }

        request.getRequestDispatcher(rstPage).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
