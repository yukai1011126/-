import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.like.mpcreator.CreedOrm;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class CreateorMybatisPlusORM {
    @Test
    public void genAllTest() {
        CreedOrm.mpgStandalone().genAll();
//        CreedOrm.mpgStandalone().genXml();
        CreedOrm.mpgStandalone().genWeb();
    }

}
