import com.like.mpcreator.CreedOrm;
import org.junit.Test;

public class CreateorMybatisPlusORM {
    @Test
    public void genAllTest() {
        CreedOrm.mpgStandalone().genAll();
//        CreedOrm.mpgStandalone().genXml();
        CreedOrm.mpgStandalone().genWeb();
    }
}
