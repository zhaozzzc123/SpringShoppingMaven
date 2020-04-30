package servlet;

import bean.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "modifyGoods")
public class ModifyGoodsServlet extends HttpServlet {

    @Autowired
    GoodsService goodsService;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AutowireCapableBeanFactory factory = wac.getAutowireCapableBeanFactory();
        factory.autowireBean(this);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsVo g = new GoodsVo();
        g.setGoodsId(Integer.parseInt(request.getParameter("goodsId").trim()));
        g.setGoodsName(request.getParameter("goodsName"));
        g.setPrice(Float.parseFloat(request.getParameter("price")));

        Integer rst = goodsService.modifyGoods(g);

        String rstPage = "getAllGoods";

        if(rst != 1){
            String errorMessage = "修改商品出错！";
            request.setAttribute("errorMessage",errorMessage);
        }else{
            String infoMessage = "修改商品成功！";
            request.setAttribute("errorMessage",infoMessage);
        }

        request.getRequestDispatcher(rstPage).forward(request,response);

    }
}
