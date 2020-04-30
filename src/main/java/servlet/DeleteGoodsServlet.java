package servlet;

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

@WebServlet(name = "/deleteGoods")
public class DeleteGoodsServlet extends HttpServlet {

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
         doGet(req, resp);


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer goodsId = Integer.parseInt(request.getParameter("goodsId"));

        Integer rst = goodsService.deleteGoods(goodsId);

        String rstPage = "getAllGoods";

        if(rst != 1){
            String errorMessage = "删除商品出错！";
            request.setAttribute("errorMessage",errorMessage);
        }else{
            String infoMessage = "删除商品成功！";
            request.setAttribute("errorMessage",infoMessage);
        }

        request.getRequestDispatcher(rstPage).forward(request,response);

    }
}
