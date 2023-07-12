package fun.tans.tools;

import org.junit.jupiter.api.Test;
import org.xm.Similarity;

/**
 * 测试文本相似度
 */
public class SimilarityTest {

    @Test
    public void test(){
        double result = Similarity.morphoSimilarity("比较喜欢JAVA", "需要喜欢JAVA的同学");
        System.out.println(result);
    }
}
