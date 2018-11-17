package heiqi.com.greenfoodapp.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
public class SolrService {
    @Autowired
    private SolrClient client;

    @RequestMapping("add")
    public String add() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", uuid);
            doc.setField("content_ik", "我是中国人, 我爱中国");

            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */

            client.add("food", doc);
            //client.commit();
            client.commit("food");
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

}
