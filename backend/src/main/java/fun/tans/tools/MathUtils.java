package fun.tans.tools;

import java.util.List;
import java.util.Optional;

public class MathUtils {

    public static void Normalizer(List<Double> nums){
        Optional<Double> maxValueOption = nums.stream().max(Double::compare);
        final Double maxValue = maxValueOption.get();
        System.out.println("maxValue" + maxValue);
        nums.replaceAll(number->number/maxValue);
    }
}
