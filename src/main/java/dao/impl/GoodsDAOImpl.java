package dao.impl;

import java.sql.Types;
import java.util.List;

import bean.vo.GoodsVo;
import dao.GoodsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("goodsDao")
public class GoodsDAOImpl implements GoodsDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<GoodsVo> getGoodsByPage(int pageNo){
		int numPerPage = 2;
		int beginIndex = (pageNo - 1)*numPerPage;

		String sql = "select * from goods limit " + beginIndex + "," +numPerPage;

		RowMapper<GoodsVo> rowMapper = new BeanPropertyRowMapper<GoodsVo>(GoodsVo.class);
		return jdbcTemplate.query(sql, rowMapper, (Object[]) null);

	}

	public int getPageCount(){

		String sql = "select count(*) from goods";

		Integer rst = jdbcTemplate.queryForObject(sql, null, Integer.class);

		return (rst-1)/2+1;

	}

	//查询信息
	public GoodsVo getGoodsById(Integer goodsId){

		String sql = "select * from goods where goodsid=?";

		RowMapper<GoodsVo> rowMapper = new BeanPropertyRowMapper<GoodsVo>(GoodsVo.class);
		GoodsVo rst = jdbcTemplate.queryForObject(sql, new Object[]{goodsId}, rowMapper);

		return rst;
	}

    //增加信息
	public Integer saveGoods(GoodsVo newGoods){
		Integer rst = -1;

		String sql = "insert into goods values (?,?,?)";

		rst = jdbcTemplate.update(sql, new Object[]{newGoods.getGoodsId(),newGoods.getGoodsName(),newGoods.getPrice()});

		return rst;
	}

	//修改信息
	public Integer modifyGoods(GoodsVo modifiedGoods){
		Integer rst = -1;

		String sql = "UPDATE goods SET goodsName='" + modifiedGoods.getGoodsName() +
				"', price = " + modifiedGoods.getPrice() + " WHERE goodsid = " +
				modifiedGoods.getGoodsId();

		rst = jdbcTemplate.update(sql);

		return rst;
	}

	//删除信息
	@Override
	public Integer deleteGoods(Integer goodsId) {
		Integer rst = -1;

		String sql = "DELETE FROM goods " + " WHERE goodsid = " + goodsId;

		rst = jdbcTemplate.update(sql);

		return rst;
	}


}
