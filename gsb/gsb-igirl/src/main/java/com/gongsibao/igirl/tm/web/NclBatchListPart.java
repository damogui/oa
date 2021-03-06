package com.gongsibao.igirl.tm.web;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.igirl.tm.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.tm.baseinfo.NclBatch;
import com.gongsibao.igirl.tm.base.INCLOneService;
import com.gongsibao.igirl.tm.base.INCLTwoService;
import com.gongsibao.igirl.tm.base.INclBatchService;
import com.gongsibao.igirl.tm.base.ITradeMarkService;

/**
 * 我的任务列表操作功能集合
 * @author Administrator
 *
 */
public class NclBatchListPart extends ListPart{
    ITradeMarkService service = ServiceFactory.create(ITradeMarkService.class);
    INCLOneService inclOneService = ServiceFactory.create(INCLOneService.class);
    INCLTwoService inclTwoService = ServiceFactory.create(INCLTwoService.class);
    INclBatchService iNclBatchService = ServiceFactory.create(INclBatchService.class);

    public String nclBatchToData(String str) throws IOException {
        Oql oql = new Oql();
        oql.setType(NclBatch.class);
        oql.setSelects("NclBatch.*");
        oql.setFilter("currentStatus=?");
        oql.getParameters().add("currentStatus",true, Types.BOOLEAN);
        NclBatch nb = iNclBatchService.queryFirst(oql);
        URL url = new URL(str);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        String s;
        StringBuffer json = new StringBuffer("");
        while ((s = reader.readLine()) != null) {
            json.append(s);
        }
        reader.close();
        String text = json.toString();
        text = text.replaceAll("\\s*","");
        JSONArray array = JSONObject.fromObject(text).getJSONArray("data");
        NCLOne one = new NCLOne();
        one.toNew();
        List<NCLTwo> nclTwos = new ArrayList<>();
        for (int i=0;i<array.size();i++){
            JSONObject js = array.getJSONObject(i);
            if (js.get("level").toString().equals("1")){
                one = new NCLOne();
                one.toNew();
                one.setCode(js.getString("code"));
                if(StringManager.isNullOrEmpty(js.getString("name"))) {
                    one.setName(js.getString("code"));
                }else {
                    one.setName(js.getString(js.getString("name")));
                }
                one.setMemo(js.getString("description"));
                one.setNclBatchId(nb.getId());
                one = inclOneService.save(one);
            }else if(js.get("level").toString().equals("3")){
                NCLTwo two = new NCLTwo();
                two.toNew();
                two.setCode(js.getString("pid"));
                two.setName(js.getString("name"));
                two.setThirdCode(js.getString("code"));
                two.setNclOneId(one.getId());
                two.setNclOne(one);
                nclTwos.add(two);
                if (nclTwos.size()==1000){
                    inclTwoService.saves(nclTwos);
                    nclTwos = new ArrayList<>();
                }
                if (i==array.size()-1){
                    inclTwoService.saves(nclTwos);
                }
            }
        }
        nb.setInsert(true);
        nb.toPersist();
        iNclBatchService.save(nb);
        return "完成尼斯数据导入";
    }
}